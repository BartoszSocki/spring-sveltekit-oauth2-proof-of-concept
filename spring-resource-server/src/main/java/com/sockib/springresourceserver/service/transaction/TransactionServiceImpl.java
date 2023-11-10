package com.sockib.springresourceserver.service.transaction;

import com.sockib.springresourceserver.model.dto.input.AddressInput;
import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.BoughtProduct;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.Transaction;
import com.sockib.springresourceserver.model.respository.BoughtProductRepository;
import com.sockib.springresourceserver.model.respository.ProductRepository;
import com.sockib.springresourceserver.model.respository.TransactionRepository;
import com.sockib.springresourceserver.model.respository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private ProductRepository productRepository;
    private BoughtProductRepository boughtProductRepository;
    private UserRepository userRepository;

    @Override
    @Transactional
    public Transaction buyProducts(List<Long> productsIds, AddressInput addressInput, String email) {
        var user = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));

        var transaction = new Transaction();
        var products = productRepository.findProductsByIdIn(productsIds);

        if (products.size() != productsIds.size()) {
            throw new RuntimeException("some products don't exists");
        }

        var areProductsAvailable = areProductsAvailable(products);
        if (!areProductsAvailable) {
            throw new RuntimeException("not all products are available");
        }

        var prices = products.stream().map(Product::getPrice).toList();
        var totalProductsPrice = getTotalPriceOfProducts(prices);

        if (!totalProductsPrice.getCurrency().equals(user.getUserMoney().getCurrency())) {
            throw new RuntimeException("TODO: implement different currencies problem");
        }

        if (totalProductsPrice.getAmount() > user.getUserMoney().getAmount()) {
            throw new RuntimeException("user has no money to buy products");
        }

        var boughtProducts = products.stream()
                .map(this::convertProductToBoughtProduct)
                .toList();
        boughtProducts.forEach(bp -> bp.setTransaction(transaction));

        transaction.setBuyer(user);
        transaction.setTransactionStatus("OK");

        transaction.setBoughtProducts(boughtProducts);

        var address = addressInput.toAddress();
        address.setUser(user);

        transaction.setAddress(address);

        // user money update
        user.getUserMoney().setAmount(user.getUserMoney().getAmount() - totalProductsPrice.getAmount());
        userRepository.save(user);
        
        var productsWithUpdatedQuantity = getProductsWithUpdatedQuantity(products);
        productRepository.saveAll(productsWithUpdatedQuantity);

        return transactionRepository.save(transaction);
    }

    private BoughtProduct convertProductToBoughtProduct(Product product) {
        var boughtProduct = new BoughtProduct();
        boughtProduct.setName(product.getName());
        boughtProduct.setDescription(product.getDescription());
        boughtProduct.setCategory(product.getCategory());
        boughtProduct.setPrice(product.getPrice());
        boughtProduct.setOwner(product.getOwner());
        boughtProduct.setImageUrl(product.getImageUrl());

        return boughtProduct;
    }

    private Money getTotalPriceOfProducts(List<Money> prices) {
        var areCurrenciesTheSame = prices.stream().allMatch(m -> "USD".equals(m.getCurrency()));
        if (!areCurrenciesTheSame) {
            throw new RuntimeException("TODO: implement different currencies total price");
        }

        var initPrice = new Money();
        initPrice.setCurrency("USD");
        initPrice.setAmount(0.0);

        var totalPrice = prices.stream().reduce(initPrice, (cur, acc) -> {
            acc.setAmount(acc.getAmount() + cur.getAmount());
            return acc;
        });
        return totalPrice;
    }

    private boolean areProductsAvailable(List<Product> products) {
        for (var product : products) {
            if ((product.getInventory().getQuantity() <= 0)) {
                return false;
            }
        }

        return true;
    }

    private List<Product> getProductsWithUpdatedQuantity(List<Product> products) {
        products.forEach(p -> p.getInventory().setQuantity(p.getInventory().getQuantity() - 1));
        return products;
    }

}
