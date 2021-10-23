package com.ssafy.web.component.product;

import com.ssafy.web.dto.ProductDto;
import com.ssafy.web.dto.ProductFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ProductComponentImpl implements ProductComponent {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductFileDto> searchProduct(String name) throws Exception {
        return productRepository.searchProduct(name);
    }

    @Override
    public void addProduct(ProductFileDto productFileDto) throws Exception {
        productRepository.insertProductWithFiles(productFileDto);
    }

    @Override
    public ProductDto findProduct(String isbn) throws Exception {
        return productRepository.findProduct(isbn);
    }

    @Override
    public void modifyProduct(ProductDto productDto) throws Exception {
        productRepository.modifyProduct(productDto);
    }

    @Override
    public void deleteProduct(ProductDto productDto) throws Exception {
        productRepository.deleteProduct(productDto);
    }
}
