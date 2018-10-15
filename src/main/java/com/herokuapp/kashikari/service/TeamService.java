package com.herokuapp.kashikari.service;

import com.herokuapp.kashikari.entity.Team;
import com.herokuapp.kashikari.form.TeamCreateForm;
import com.herokuapp.kashikari.form.TeamUpdateForm;
import com.herokuapp.kashikari.repository.TeamRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public String create(TeamCreateForm form) {
        Team team = new Team();
        BeanUtils.copyProperties(form, team);
        String createId = UUID.randomUUID().toString();
        team.setId(createId);
        teamRepository.create(team);
        return createId;
    }

    public Team getById(String teamId) {
        return teamRepository.selectById(teamId);
    }

    public List<Team> list() {
        return teamRepository.selectAll();
    }

    public void update(TeamUpdateForm form, String teamId) {
        Team team = new Team();
        BeanUtils.copyProperties(form, team);
        team.setId(teamId);
        teamRepository.update(team);
    }

    public void delete(String teamId) {
        teamRepository.deleteByTeamId(teamId);
    }
}
