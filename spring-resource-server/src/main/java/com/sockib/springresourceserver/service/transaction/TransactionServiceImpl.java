package com.sockib.springresourceserver.service.transaction;

import com.sockib.springresourceserver.model.dto.input.AddressInput;
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
    public void buyProducts(List<Long> productsIds, AddressInput address, String email) {
        var user = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));

        var products = productRepository.findProductsByIdIn(productsIds).stream()
                .map(this::convertProductToBoughtProduct)
                .toList();

        var transaction = new Transaction();
        transaction.setBuyer(user);
        transaction.setTransactionStatus("DEFAULT");

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

}
