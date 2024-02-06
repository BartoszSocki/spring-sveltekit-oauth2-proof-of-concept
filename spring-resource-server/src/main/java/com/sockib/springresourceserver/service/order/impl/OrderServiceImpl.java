package com.sockib.springresourceserver.service.order.impl;

import com.sockib.springresourceserver.exception.*;
import com.sockib.springresourceserver.model.dto.request.OrderLineItemRequestDto;
import com.sockib.springresourceserver.model.dto.request.OrderRequestDto;
import com.sockib.springresourceserver.model.value.Address;
import com.sockib.springresourceserver.model.entity.Order;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.User;
import com.sockib.springresourceserver.model.respository.OrderRepository;
import com.sockib.springresourceserver.model.respository.UserRepository;
import com.sockib.springresourceserver.model.respository.product.ProductRepository;
import com.sockib.springresourceserver.model.value.Money;
import com.sockib.springresourceserver.service.order.LineItemFactory;
import com.sockib.springresourceserver.service.order.OrderService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            UserRepository userRepository,
                            ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void buyProducts(OrderRequestDto orderRequestDto, String buyerEmail) {
        var buyer = userRepository.findUserByEmail(buyerEmail)
                .orElseThrow(() -> new UsernameNotFoundException("user with email: " + buyerEmail + " not found"));

        var productsIds = orderRequestDto.getProducts().stream()
                .map(OrderLineItemRequestDto::getProductId)
                .toList();

        var productsQuantities = orderRequestDto.getProducts().stream()
                .map(OrderLineItemRequestDto::getProductQuantity)
                .toList();

        var products = productRepository.findProductsByIdIn(productsIds);

        if (!allProductsExist(products, productsIds)) {
            throw new ProductNotExistException("there is at least one product in: " + productsIds + " that don't exist");
        }
        if (!areProductsAvailable(products, productsQuantities)) {
            throw new ProductNotAvailableException("there is at least one product in: " + productsIds + " that is not available");
        }

        var lineItems = products.stream()
                .map(LineItemFactory::create)
                .toList();

        var order = Order.bought(
                buyer,
                lineItems,
                modelMapper.map(orderRequestDto.getAddress(), Address.class)
        );

        // TODO: rethink how to lock products in db
        updateProductsQuantities(products, productsQuantities);
        updateBuyerMoney(buyer, order.calculateTotal());

        orderRepository.save(order);
    }

    private boolean allProductsExist(List<Product> products, List<Long> productsIds) {
        return productsIds.size() == products.size();
    }

    private boolean areProductsAvailable(List<Product> products, List<Integer> quantities) {
        return IntStream.range(0, quantities.size())
                .allMatch(i -> products.get(i).getInventory().getQuantity() >= quantities.get(i));
    }

    private void updateProductsQuantities(List<Product> products, List<Integer> quantities) {
        IntStream.range(0, quantities.size())
                .forEach(i -> products.get(i).getInventory().setQuantity(products.get(i).getInventory().getQuantity() - quantities.get(i)));

        productRepository.saveAll(products);
    }

    private void updateBuyerMoney(User user, Money money) {
        user.setUserMoney(user.getUserMoney().subtract(money));
        userRepository.save(user);
    }

}
