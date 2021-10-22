package com.ssafy.web.component.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class CommentComponentImpl implements CommentComponent {

    @Autowired
    CommentRepository commentRepository;
}
