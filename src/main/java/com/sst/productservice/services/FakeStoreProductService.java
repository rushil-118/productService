package com.sst.productservice.services;

import com.sst.productservice.dtos.FakeStoreProductDto;
import com.sst.productservice.exceptions.ProductNotFoundException;
import com.sst.productservice.models.Category;
import com.sst.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service("fakestoreproductservice")
public class FakeStoreProductService implements productService{
    RestTemplate restTemplate = new RestTemplate();

    public Product convertDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(Long.valueOf(fakeStoreProductDto.getId()));
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class);
        if(fakeStoreProductDto==null) {
            throw new ProductNotFoundException(id,"Please pass a valid productId");
        }
        return convertDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> list = new ArrayList<>();

        if(fakeStoreProductDto==null) return null;

        for(FakeStoreProductDto fakeStoreProductDto1 : fakeStoreProductDto){
            list.add(convertDtoToProduct(fakeStoreProductDto1));
        }
        return list;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
