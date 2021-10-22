package com.ssafy.web.component.product;

import com.ssafy.web.dto.ProductFileDto;

import java.util.List;

interface ProductRepository {
    List<ProductFileDto> searchProduct(String name) throws Exception;

    void insertProductWithFiles(ProductFileDto productFileDto) throws Exception;
}
