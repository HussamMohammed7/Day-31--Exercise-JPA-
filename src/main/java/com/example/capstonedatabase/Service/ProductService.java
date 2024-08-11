package com.example.capstonedatabase.Service;

import com.example.capstonedatabase.Model.Category;
import com.example.capstonedatabase.Model.Product;
import com.example.capstonedatabase.Repository.CategoryRepository;
import com.example.capstonedatabase.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public boolean addProduct(Product product) {
        Category checkCat = categoryRepository.getById(product.getCategoryID());
        if (checkCat.getName() == null) {
            return false;
        }
        productRepository.save(product);
        return true;
    }

    public boolean updateProduct(Integer id, Product product) {
        Product updatedProduct = productRepository.getById(id);
        if (updatedProduct.getName() == null) {
            return false;
        }

        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setCategoryID(product.getCategoryID());
            productRepository.save(updatedProduct);
            return true;

    }

    public boolean removeProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public List<Product> discountProductsOver(double price, double discountPercentage) {
        List<Product> discountedProducts = new ArrayList<>();
        for (Product product : getProducts()) {
            if (product.getPrice() >= price) {
                double discountedPrice = product.getPrice() * (1 - (discountPercentage / 100));
                product.setPrice(discountedPrice);
                productRepository.save(product);
                discountedProducts.add(product);
            }
        }
        return discountedProducts;
    }
    public ArrayList<Product> getProductsByCategoryId(Integer categoryId) {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : getProducts()) {
            if (product.getCategoryID() == (categoryId)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }


    public Boolean deleteAllProductsCategory(Integer categoryId) {
        List<Product> productsToDelete = getProducts();
        if (productsToDelete.isEmpty()) {
            return false;
        }
        for (Product product : productsToDelete) {
            if (product.getCategoryID() == categoryId) {
                productRepository.delete(product);

            }
        }

        return true;
    }
}
