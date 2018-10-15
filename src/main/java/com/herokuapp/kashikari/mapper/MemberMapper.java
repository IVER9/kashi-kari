package com.herokuapp.kashikari.mapper;

import com.herokuapp.kashikari.entity.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO member(id, team_id, name, money, created_at, updated_at, deleted_flag)VALUES (#{id},#{teamId},#{name}, #{money}, now(), now(), '0')")
    void insert(Member member);

    @Select("SELECT * from member where team_id = #{teamId} and deleted_flag = '0' order by updated_at")
    List<Member> selectByTeamId(String teamId);

    @Select("SELECT * from member where id = #{memberId} and deleted_flag = '0'")
    Member selectByMemberId(String memberId);

    @Update("UPDATE member SET name = #{name} , money = #{money}, updated_at = now() WHERE  id = #{id}")
    void update(Member member);

    @Update("UPDATE member SET deleted_flag = '1', updated_at = now() WHERE  id = #{memberId}")
    void deleteByMemberId(String memberId);
}
