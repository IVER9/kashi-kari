package com.herokuapp.kashikari.controller;

import com.herokuapp.kashikari.entity.Member;
import com.herokuapp.kashikari.entity.Team;
import com.herokuapp.kashikari.form.MemberCreateForm;
import com.herokuapp.kashikari.service.MemberService;
import com.herokuapp.kashikari.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private MemberService memberService;

    @PostMapping(path = "/team/{teamId}/{url}/member")
    public String memberCreate(@PathVariable String teamId,@PathVariable String url, @Validated @ModelAttribute MemberCreateForm form,
                             BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()) {
            attr.addFlashAttribute("memberCreateError", true);
            return "redirect:/team/" + teamId + "/" + url;
        }
        try {
            memberService.create(form, teamId);
        } catch(RuntimeException e) {
            log.error(e.getMessage());
            attr.addFlashAttribute("memberCreateError", true);
            return "redirect:/team/" + teamId + "/" + url;
        }
        attr.addFlashAttribute("memberCreateSuccess", true);
        return "redirect:/team/" + teamId + "/" + url;
    }

    @GetMapping(path="/team/{teamId}/{url}/member/{memberId}/setting")
    public String memberSetting(@PathVariable String teamId,@PathVariable String url,@PathVariable String memberId, Model model){
        Team team = teamService.getById(teamId);
        model.addAttribute("team",team);

        Member member = memberService.getById(memberId);
        model.addAttribute("member",member);
        return "memberSetting";
    }

    @PostMapping(path = "/team/{teamId}/{url}/member/{memberId}",params = "update")
    public String memberUpdate(@PathVariable String teamId,@PathVariable String url, @PathVariable String memberId,  @Validated @ModelAttribute MemberCreateForm form,
                             BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()) {
            attr.addFlashAttribute("memberUpdateError", true);
            return "redirect:/team/" + teamId + "/" + url + "/member/" + memberId + "/setting";
        }
        try {
            memberService.update(form, memberId);
        } catch(RuntimeException e) {
            log.error(e.getMessage());
            attr.addFlashAttribute("memberUpdateError", true);
            return "redirect:/team/" + teamId + "/" + url + "/member/" + memberId + "/setting";
        }
        attr.addFlashAttribute("memberUpdateSuccess", true);
        return "redirect:/team/" + teamId + "/" + url;
    }

    @PostMapping(path = "/team/{teamId}/{url}/member/{memberId}",params = "delete")
    public String memberDelete(@PathVariable String teamId,@PathVariable String url,@PathVariable String memberId,
                             RedirectAttributes attr){
        try {
            memberService.delete(memberId);
        } catch(RuntimeException e) {
            log.error(e.getMessage());
            attr.addFlashAttribute("memberDeleteError", true);
            return "redirect:/team/" + teamId + "/" + url + "/member/" + memberId + "/setting";
        }
        return "redirect:/team/" + teamId + "/" + url;
    }
}
