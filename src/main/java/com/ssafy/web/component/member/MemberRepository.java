package com.ssafy.web.component.member;

import com.ssafy.web.dto.MemberDto;

interface MemberRepository {
    MemberDto findMember(MemberDto memberDto) throws Exception;

    void insertMember(MemberDto memberDto) throws Exception;
}
