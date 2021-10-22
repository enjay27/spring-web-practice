package com.ssafy.web.controller;

import com.ssafy.web.component.post.PostComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PostController {

    @Autowired
    PostComponent postComponent;

}
