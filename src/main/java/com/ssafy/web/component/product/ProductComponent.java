package com.ssafy.web.component.product;

import com.ssafy.web.dto.ProductDto;
import com.ssafy.web.dto.ProductFileDto;

import java.util.List;

public interface ProductComponent {
    List<ProductFileDto> searchProduct(String name) throws Exception;

    void addProduct(ProductFileDto productFileDto) throws Exception;

    ProductDto findProduct(String isbn) throws Exception;

    void modifyProduct(ProductDto productDto) throws Exception;

    void deleteProduct(ProductDto productDto) throws Exception;
}
