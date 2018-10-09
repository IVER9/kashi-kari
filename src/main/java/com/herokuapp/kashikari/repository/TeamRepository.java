package com.herokuapp.kashikari.repository;

import com.herokuapp.kashikari.entity.Team;
import com.herokuapp.kashikari.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamRepository {
    @Autowired
    private TeamMapper teamMapper;
    public void create(Team team) {
        teamMapper.insert(team);
    }

    public Team selectById(String teamId) {
        return teamMapper.selectById(teamId);
    }

    public List<Team> selectAll() {
        return teamMapper.selectAll();
    }

    public void update(Team team) {
        teamMapper.update(team);
    }

    public void deleteByTeamId(String teamId) {
        teamMapper.deleteByTeamId(teamId);
    }
}
