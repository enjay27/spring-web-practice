package com.ssafy.web.dto;

public class ProductDto {
    String isbn;
    String member_id;
    String name;
    int price;
    String explanation;

    public ProductDto() {
    }

    public ProductDto(String isbn, String member_id, String name, int price, String explanation) {
        this.isbn = isbn;
        this.member_id = member_id;
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

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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
