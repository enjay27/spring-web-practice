package com.ssafy.web.component.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class MemberComponentImpl implements MemberComponent {

    @Autowired
    MemberRepository memberRepository;
}
