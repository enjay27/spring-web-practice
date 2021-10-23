package com.ssafy.web.component.member;

import com.ssafy.web.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class MemberComponentImpl implements MemberComponent {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public MemberDto authenticate(MemberDto memberDto) throws Exception {
        MemberDto findMember = memberRepository.findMember(memberDto);
        if (findMember == null) throw new Exception("로그인 실패");
        if (!findMember.getPw().equals(memberDto.getPw())) throw new Exception("로그인 실패");
        return findMember;
    }

    @Override
    public void signUp(MemberDto memberDto) throws Exception {
        memberRepository.insertMember(memberDto);
    }
}
