package com.myapp.tutoriel.Product.commandhandlers;

import com.myapp.tutoriel.Command;
import com.myapp.tutoriel.Exceptions.ProductNotFoundException;
import com.myapp.tutoriel.Product.Model.Product;
import com.myapp.tutoriel.Product.Model.UpdateProductCommand;
import com.myapp.tutoriel.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ResponseEntity> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<ResponseEntity> execute(UpdateProductCommand command) {
        Optional<Product> optionalProduct = productRepository.findById(command.getId());
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException();
        }

        Product product = command.getProduct();
        product.setId(command.getId());
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }
}
