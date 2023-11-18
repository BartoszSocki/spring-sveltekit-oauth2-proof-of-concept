package com.sockib.springresourceserver.service.order;

import com.sockib.springresourceserver.model.dto.input.AddressInput;
import com.sockib.springresourceserver.model.dto.input.OrderProductInput;
import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.Address;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.Order;
import com.sockib.springresourceserver.model.entity.User;
import com.sockib.springresourceserver.model.exception.*;
import com.sockib.springresourceserver.model.respository.products.ProductRepository;
import com.sockib.springresourceserver.model.respository.OrderRepository;
import com.sockib.springresourceserver.model.respository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private ProductToBoughtProductConverter productToBoughtProductConverter;
    private ModelMapper modelMapper;
    private final AddressRepository addressRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            UserRepository userRepository,
                            ModelMapper modelMapper,
                            AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productToBoughtProductConverter = new ProductToBoughtProductConverter();
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public void buyProducts(List<OrderProductInput> productsInput, AddressInput addressInput, String email) {
        var buyer = userRepository.findUserByEmail(email).orElse(new User(email, new Money(1000.0, "USD")));
        var productsIds = productsInput.stream()
                .map(OrderProductInput::getProductId)
                .toList();

        var productsQuantities = productsInput.stream()
                .map(OrderProductInput::getProductQuantity)
                .toList();

        var products = productRepository.findProductsByIdIn(productsIds);

        if (!allProductsExist(products, productsIds)) {
            throw new ProductNotExistException("there is at least one product in: " + productsIds + " that don't exist");
        }
        if (!areProductsAvailable(products, productsQuantities)) {
            throw new ProductNotAvailable("there is at least one product in: " + productsIds + " that is not available");
        }
        if (!userDontOwnProducts(products, buyer)) {
            throw new InvalidOrderException("owner cannot buy own product");
        }
        if (!canUserBuyProducts(products, productsQuantities, buyer)) {
            throw new NotEnoughCashException("user don't have enough cash");
        }

        var boughtProducts = products.stream()
                .map(productToBoughtProductConverter::convert)
                .toList();

        boughtProducts.forEach(b -> b.setOwner(buyer));

        var address = addressRepository.findByAllFieldsAndUserId(addressInput.getCountry(),
                        addressInput.getCity(),
                        addressInput.getAddressLine(),
                        addressInput.getPostalCode(),
                        buyer.getId())
                .orElse(modelMapper.map(addressInput, Address.class));
        address.setUser(buyer);

        var order = new Order();
        order.setBuyer(buyer);
        order.setOrderStatus("BOUGHT");
        order.setBoughtProducts(boughtProducts);
        order.setAddress(address);
        boughtProducts.forEach(bp -> bp.setOrder(order));

        var priceSum = getProductPriceSum(products, productsQuantities);
        updateBuyerMoney(buyer, priceSum);

        updateProductsQuantities(products, productsQuantities);

        orderRepository.save(order);
    }

    private Double getProductPriceSum(List<Product> products, List<Integer> quantities) {
        return IntStream.range(0, quantities.size())
                .mapToObj(i -> products.get(i).getPrice().getAmount() * quantities.get(i))
                .reduce(0.0, Double::sum);
    }

    private boolean canUserBuyProducts(List<Product> products, List<Integer> quantities, User user) {
        var areCurrenciesTheSame = products.stream().allMatch(p -> "USD".equals(p.getPrice().getCurrency()));
        if (!areCurrenciesTheSame) {
            throw new MixedCurrenciesException("mixed currencies");
        }

        var priceSum = getProductPriceSum(products, quantities);
        var userMoneyAmount = user.getUserMoney().getAmount();

        return priceSum <= userMoneyAmount;
    }

    private boolean allProductsExist(List<Product> products, List<Long> productsIds) {
        return productsIds.size() == products.size();
    }

    private boolean areProductsAvailable(List<Product> products, List<Integer> quantities) {
        return IntStream.range(0, quantities.size())
                .allMatch(i -> products.get(i).getInventory().getQuantity() >= quantities.get(i));
    }

    private boolean userDontOwnProducts(List<Product> products, User buyer) {
        return products.stream().noneMatch(p -> Objects.equals(p.getOwner().getId(), buyer.getId()));
    }

    private void updateProductsQuantities(List<Product> products, List<Integer> quantities) {
        IntStream.range(0, quantities.size())
                .forEach(i -> products.get(i).getInventory().setQuantity(products.get(i).getInventory().getQuantity() - quantities.get(i)));

        productRepository.saveAll(products);
    }

    private void updateBuyerMoney(User user, Double amount) {
        var currentUserMoney = user.getUserMoney().getAmount();
        user.getUserMoney().setAmount(currentUserMoney - amount);
        userRepository.save(user);
    }

}
