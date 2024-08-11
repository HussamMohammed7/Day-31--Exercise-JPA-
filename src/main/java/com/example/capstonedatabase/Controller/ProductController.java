package com.example.capstonedatabase.Controller;

import com.example.capstonedatabase.Api.ApiResponse;
import com.example.capstonedatabase.Model.Product;
import com.example.capstonedatabase.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    public final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        if (productService.getProducts().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Product list is empty")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                productService.getProducts()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        boolean response = productService.addProduct(product);
        if (response) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Product added successfully")
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse("Product not added successfully, category not found ")
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        if (productService.updateProduct(id, product)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Product updated successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Product not found")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        if (productService.removeProduct(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Product deleted successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Product not found or empty")
            );
        }
    }

    @GetMapping("/get/byCategory/{categoryId}")
    public ResponseEntity getProductsByCategoryId(@PathVariable Integer categoryId) {

        if (productService.getProductsByCategoryId(categoryId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Product list in this category is empty")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByCategoryId(categoryId));
    }


    @PutMapping("/discount-products")
    public ResponseEntity getDiscountedProducts(
            @RequestParam double price,
            @RequestParam double discountPercentage) {

        System.out.println(price);
        if (productService.discountProductsOver(price, discountPercentage).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("No products found over the specified price")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                productService.discountProductsOver(price, discountPercentage)
        );
    }

    @DeleteMapping ("/delete/category")
    public ResponseEntity deleteAllcategoryies(@RequestParam Integer categoryId){

        boolean result = productService.deleteAllProductsCategory(categoryId);

        if (result){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Category deleted successfully")
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse("Category not found")
        );

    }

}
