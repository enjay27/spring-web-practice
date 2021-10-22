package com.ssafy.web.controller;

import com.ssafy.web.component.member.MemberComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    @Autowired
    MemberComponent memberComponent;

}
