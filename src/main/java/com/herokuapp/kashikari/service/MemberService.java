package com.herokuapp.kashikari.service;

import com.herokuapp.kashikari.dto.LoanDto;
import com.herokuapp.kashikari.entity.Loan;
import com.herokuapp.kashikari.entity.Member;
import com.herokuapp.kashikari.form.MemberCreateForm;
import com.herokuapp.kashikari.repository.MemberRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    public void create(MemberCreateForm form, String teamId) {
        Member member = new Member();
        BeanUtils.copyProperties(form, member);
        String createId = UUID.randomUUID().toString();
        member.setId(createId);
        member.setTeamId(teamId);
        memberRepository.create(member);
    }

    public List<Member> getByTeamId(String teamId) {
        return memberRepository.selectByTeamId(teamId);
    }

    public Member getById(String memberId) {
        return memberRepository.selectByMemberId(memberId);
    }

    public void update(MemberCreateForm form, String memberId) {
        Member member = new Member();
        BeanUtils.copyProperties(form, member);
        member.setId(memberId);
        memberRepository.update(member);
    }

    public void delete(String memberId) {
        memberRepository.deleteByMemberId(memberId);
    }
}
