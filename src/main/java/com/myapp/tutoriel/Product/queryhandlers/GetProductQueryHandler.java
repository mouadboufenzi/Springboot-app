package com.myapp.tutoriel.Product.queryhandlers;

import com.myapp.tutoriel.Exceptions.ProductNotFoundException;
import com.myapp.tutoriel.Product.Model.Product;
import com.myapp.tutoriel.Product.Model.ProductDTO;
import com.myapp.tutoriel.Product.ProductRepository;
import com.myapp.tutoriel.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductQueryHandler implements Query<Integer, ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ProductDTO> execute(Integer id) {
        Optional<ProductDTO> productDTO = productRepository
                .findById(id)
                .map(ProductDTO::new);
        if (productDTO.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return ResponseEntity.ok(productDTO.get());
    }
}
