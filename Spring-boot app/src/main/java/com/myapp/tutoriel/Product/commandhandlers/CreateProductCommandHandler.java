package com.myapp.tutoriel.Product.commandhandlers;

import com.myapp.tutoriel.Command;
import com.myapp.tutoriel.Product.Model.Product;
import com.myapp.tutoriel.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCommandHandler implements Command<Product, ResponseEntity> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity execute(Product product) {

        validateProduct(product);

        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    private void validateProduct(Product product) {

        if(StringUtils.isBlank(product.getName())){
            throw new RuntimeException("Product name is empty");
        }

        if(StringUtils.isBlank(product.getDescription())){
            throw new RuntimeException("Product description is empty");
        }

        if(product.getPrice() <= 0.0){
            throw new RuntimeException("Product price is invalid");
        }

        if(product.getQuantity() <= 0){
            throw new RuntimeException("Product quantity is invalid");
        }
    }
}
