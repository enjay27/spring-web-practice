package com.ssafy.web.dto;

public class ProductDto {
    String isbn;
    String memberId;
    String name;
    int price;
    String explanation;

    public ProductDto() {
    }

    public ProductDto(String isbn, String memberId, String name, int price, String explanation) {
        this.isbn = isbn;
        this.memberId = memberId;
        this.name = name;
        this.price = price;
        this.explanation = explanation;
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
}
