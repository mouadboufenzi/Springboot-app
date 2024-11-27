package com.myapp.tutoriel.Product.queryhandlers;

import com.myapp.tutoriel.Product.Model.Product;
import com.myapp.tutoriel.Product.Model.ProductDTO;
import com.myapp.tutoriel.Product.ProductRepository;
import com.myapp.tutoriel.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<ProductDTO>> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<ProductDTO> productDTOs = productRepository
                .findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();
        return ResponseEntity.ok(productDTOs);
    }
}
