package io.github.guilhermeabroncari.rest.controller;

import io.github.guilhermeabroncari.domain.entity.Product;
import io.github.guilhermeabroncari.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @Transactional
    @ResponseStatus(CREATED)
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(NO_CONTENT)
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productRepository.findById(id).map(atualProduct -> {
            product.setId(atualProduct.getId());
            productRepository.save(product);
            return product;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }

    @GetMapping
    public List<Product> findProductWithParam(Product filter) {
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        return productRepository.findAll(example);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return product;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }
}
