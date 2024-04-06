package com.sst.productservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    private String id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;
}
