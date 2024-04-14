package com.sst.productservice.services;

import com.sst.productservice.exceptions.CategoryNotFoundException;
import com.sst.productservice.exceptions.ProductNotFoundException;
import com.sst.productservice.models.Category;
import com.sst.productservice.models.Product;
import com.sst.productservice.repositories.CategoryRepository;
import com.sst.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfproductservice")
public class SelfProductService implements productService{
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(id,"Product Id not found");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
         return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();
        if(category.getId() == null){
            product.setCategory(categoryRepository.save(category));
        }
        Product product1 = productRepository.save(product);
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("Invalid Category id");
        }
        product1.setCategory(optionalCategory.get());
        return product1;
    }
}
