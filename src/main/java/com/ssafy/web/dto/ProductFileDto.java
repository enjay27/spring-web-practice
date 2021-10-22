package com.ssafy.web.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductFileDto {
    String isbn;
    String memberId;
    String name;
    int price;
    String explanation;
    List<FileDto> files;

    public ProductFileDto() {
    }

    public ProductFileDto(ProductDto productDto) {
        this.isbn = productDto.getIsbn();
        this.memberId = productDto.getMemberId();
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.explanation = productDto.getExplanation();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<FileDto> getFiles() {
        return files;
    }

    public void setFiles(List<FileDto> files) {
        this.files = files;
    }
}
