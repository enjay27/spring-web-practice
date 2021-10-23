package com.ssafy.web.controller;

import com.ssafy.web.component.member.MemberComponent;
import com.ssafy.web.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    MemberComponent memberComponent;

    @GetMapping("/member/login")
    public String loginPage() {
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session) throws Exception {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(id);
        memberDto.setPw(pw);
        MemberDto authenticated = memberComponent.authenticate(memberDto);
        session.setAttribute("userinfo", authenticated);

        return "index";
    }

    @GetMapping("/member/join")
    public String joinPage() {
        return "member/join";
    }

    @PostMapping("/member/join")
    public String join(MemberDto memberDto, HttpSession session) throws Exception {
        memberComponent.signUp(memberDto);
        session.setAttribute("userinfo", memberComponent.authenticate(memberDto));
        return "index";
    }
}
