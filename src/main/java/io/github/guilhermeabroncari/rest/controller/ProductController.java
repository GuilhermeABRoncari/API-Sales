package io.github.guilhermeabroncari.rest.controller;

import io.github.guilhermeabroncari.domain.entity.Product;
import io.github.guilhermeabroncari.domain.repository.ProductRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/products")
@Api("API Products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @Transactional
    @ResponseStatus(CREATED)
    @ApiOperation("Saves a new Product in database.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Created a new product successfully."),
            @ApiResponse(code = 400, message = "Validation error.")
    })
    public Product saveProduct(@RequestBody @Valid Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a product parameters by ID.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Not return any content but make the updates of product in database."),
            @ApiResponse(code = 404, message = "Product not found in database.")
    })
    public void updateProduct(@PathVariable @ApiParam("Product ID.") Long id, @RequestBody @Valid Product product) {
        productRepository.findById(id).map(atualProduct -> {
            product.setId(atualProduct.getId());
            productRepository.save(product);
            return product;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }

    @GetMapping
    @ApiOperation("Make a list of existing products and filter on your parameters.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found.")
    })
    public List<Product> findProductWithParam(Product filter) {
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        return productRepository.findAll(example);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a product in database by ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found."),
            @ApiResponse(code = 404, message = "Product not found by ID.")
    })
    public Product getProductById(@PathVariable @ApiParam("Product ID.") Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a product by ID.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Product deleted by ID."),
            @ApiResponse(code = 404, message = "Product not found in database.")
    })
    public void deleteProduct(@PathVariable @ApiParam("Product ID.") Long id) {
        productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return product;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }
}
