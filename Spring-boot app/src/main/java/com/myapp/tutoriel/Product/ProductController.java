package com.myapp.tutoriel.Product;

import com.myapp.tutoriel.Exceptions.ProductNotFoundException;
import com.myapp.tutoriel.Product.Model.Product;
import com.myapp.tutoriel.Product.Model.ProductDTO;
import com.myapp.tutoriel.Product.Model.UpdateProductCommand;
import com.myapp.tutoriel.Product.commandhandlers.CreateProductCommandHandler;
import com.myapp.tutoriel.Product.commandhandlers.DeleteProductCommandHandler;
import com.myapp.tutoriel.Product.commandhandlers.UpdateProductCommandHandler;
import com.myapp.tutoriel.Product.queryhandlers.GetAllProductsQueryHandler;
import com.myapp.tutoriel.Product.queryhandlers.GetProductQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GetAllProductsQueryHandler getAllProductsQueryHandler;

    @Autowired
    private GetProductQueryHandler getProductQueryHandler;

    @Autowired
    private CreateProductCommandHandler createProductCommandHandler;

    @Autowired
    private UpdateProductCommandHandler updateProductCommandHandler;

    @Autowired
    private DeleteProductCommandHandler deleteProductCommandHandler;
    //CRUD
    //Post, Get, Put, Delete
    //ResponseEntity.ok => status of 200

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
       return getAllProductsQueryHandler.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int id) {
        return getProductQueryHandler.execute(id);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product) {
        return createProductCommandHandler.execute(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        UpdateProductCommand command = new UpdateProductCommand(id, product);
        return updateProductCommandHandler.execute(command);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        return deleteProductCommandHandler.execute(id);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException() {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
}
