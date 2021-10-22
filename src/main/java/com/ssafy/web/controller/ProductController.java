package com.ssafy.web.controller;

import com.ssafy.web.component.product.ProductComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

    @Autowired
    ProductComponent productComponent;
}
