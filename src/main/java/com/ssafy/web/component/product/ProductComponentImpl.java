package com.ssafy.web.component.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ProductComponentImpl implements ProductComponent {

    @Autowired
    ProductRepository productRepository;
}
