package com.ssafy.web.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PostDto {
    String member_id;
    String title;
    String content;
    Date date;

    public PostDto() {
    }

    public PostDto(String member_id, String title, String content, Date date) {
        this.member_id = member_id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
