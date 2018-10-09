package com.herokuapp.kashikari.repository;

import com.herokuapp.kashikari.entity.Member;
import com.herokuapp.kashikari.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    private MemberMapper memberMapper;

    public void create(Member member) {
        memberMapper.insert(member);
    }

    public List<Member> selectByTeamId(String teamId) {
        return memberMapper.selectByTeamId(teamId);
    }

    public Member selectByMemberId(String memberId) {
        return memberMapper.selectByMemberId(memberId);
    }

    public void update(Member member) {
        memberMapper.update(member);
    }

    public void deleteByMemberId(String memberId) {
        memberMapper.deleteByMemberId(memberId);
    }
}
