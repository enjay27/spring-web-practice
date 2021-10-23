package com.ssafy.web.component.member;

import com.ssafy.web.dto.MemberDto;

public interface MemberComponent {
    MemberDto authenticate(MemberDto memberDto) throws Exception;

    void signUp(MemberDto memberDto) throws Exception;
}
