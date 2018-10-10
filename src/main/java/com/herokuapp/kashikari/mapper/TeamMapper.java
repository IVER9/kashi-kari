package com.herokuapp.kashikari.mapper;

import com.herokuapp.kashikari.entity.Team;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TeamMapper {
    @Insert("INSERT INTO team (id, url, name, created_at, updated_at, deleted_flag) VALUES (#{id},#{url}, #{name}, now(), now(), '0')")
    void insert(Team team);

    @Select("SELECT * from team where id = #{teamId} and deleted_flag = '0'")
    Team selectById(String teamId);

    @Select("SELECT * from team where deleted_flag = '0' order by created_at")
    List<Team> selectAll();

    @Update("UPDATE team SET name = #{name}, updated_at = now() WHERE id = #{id}")
    void update(Team team);

    @Update("UPDATE team SET deleted_flag = '1', updated_at = now() WHERE id = #{teamId}")
    void deleteByTeamId(String teamId);
}
