package com.ssafy.web.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CommentDto {
    String member_id;
    String product_id;
    String content;
    Date date;

    public CommentDto() {
    }

    public CommentDto(String member_id, String product_id, String content, Date date) {
        this.member_id = member_id;
        this.product_id = product_id;
        this.content = content;
        this.date = date;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
