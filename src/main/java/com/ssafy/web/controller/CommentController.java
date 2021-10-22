package com.ssafy.web.controller;

import com.ssafy.web.component.post.CommentComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {

    @Autowired
    CommentComponent commentComponent;

}
