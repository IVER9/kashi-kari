package com.herokuapp.kashikari.controller;

import com.herokuapp.kashikari.entity.Team;
import com.herokuapp.kashikari.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private TeamService teamService;

    @GetMapping(path = "/")
    public String home(Model model){
        List<Team> teamList = teamService.list();
        model.addAttribute("teams", teamList);
        return "home";
    }
}
