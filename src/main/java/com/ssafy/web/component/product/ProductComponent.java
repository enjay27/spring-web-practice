package com.ssafy.web.component.product;

import com.ssafy.web.dto.ProductFileDto;

import java.util.List;

public interface ProductComponent {
    List<ProductFileDto> searchProduct(String name) throws Exception;

    void addProduct(ProductFileDto productFileDto) throws Exception;
}
