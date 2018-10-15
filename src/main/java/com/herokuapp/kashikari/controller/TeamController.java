package com.herokuapp.kashikari.controller;

import com.herokuapp.kashikari.dto.LoanDto;
import com.herokuapp.kashikari.dto.MemberDto;
import com.herokuapp.kashikari.entity.Member;
import com.herokuapp.kashikari.entity.Team;
import com.herokuapp.kashikari.form.TeamCreateForm;
import com.herokuapp.kashikari.form.TeamUpdateForm;
import com.herokuapp.kashikari.service.LoanService;
import com.herokuapp.kashikari.service.MemberService;
import com.herokuapp.kashikari.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private LoanService loanService;

    @PostMapping(path = "/team")
    public String teamCreate(@Validated @ModelAttribute TeamCreateForm form,
                             BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()) {
            attr.addFlashAttribute("teamCreateError", true);
            return "redirect:/";
        }
        String createId = "";
        try {
            createId = teamService.create(form);
            if(createId.equals("")){
                attr.addFlashAttribute("teamCreateError", true);
                return "redirect:/";
            }
        } catch(RuntimeException e) {
            log.error(e.getMessage());
            attr.addFlashAttribute("teamCreateError", true);
            return "redirect:/";
        }
        attr.addFlashAttribute("teamCreateSuccess", true);
        return "redirect:/team/" + createId + "/" + form.getUrl();
    }

    @GetMapping(path="/team/{teamId}/{url}")
    public String team(@PathVariable String teamId,@PathVariable String url, Model model){
        Team team = teamService.getById(teamId);
        model.addAttribute("team",team);

        List<Member> memberList = memberService.getByTeamId(teamId);
        model.addAttribute("members",memberList);

        List<MemberDto> memberDtos = new ArrayList<>();
        for(Member member : memberList) {
            MemberDto memberDto = new MemberDto();
            BeanUtils.copyProperties(member, memberDto);
            memberDto.setLoanFrom(loanService.getLoanFrom(member.getId()));
            Long loanFromSumTime = (long) 0;
            for(LoanDto from : memberDto.getLoanFrom()) {
                Member fromMember = memberService.getById(from.getFromMemberId());
                loanFromSumTime += from.getTime() * fromMember.getMoney();
            }
            memberDto.setFromMoneySum(loanFromSumTime);

            Long loanToSumMoney = (long) 0;
            memberDto.setLoanTo(loanService.getLoanTo(member.getId()));
            for(LoanDto to : memberDto.getLoanTo()) {
                loanToSumMoney += to.getTime();
            }
            memberDto.setToMoneySum(loanToSumMoney * member.getMoney());
            memberDtos.add(memberDto);
        }
        model.addAttribute("memberDtoList",memberDtos);
        return "team";
    }

    @GetMapping(path="/team/{teamId}/{url}/setting")
    public String teamSetting(@PathVariable String teamId,@PathVariable String url, Model model){
        Team team = teamService.getById(teamId);
        model.addAttribute("team",team);
        return "teamSetting";
    }

    @PostMapping(path = "/team/{teamId}/{url}",params = "update")
    public String teamUpdate(@PathVariable String teamId,@PathVariable String url, @Validated @ModelAttribute TeamUpdateForm form,
                             BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()) {
            attr.addFlashAttribute("teamUpdateError", true);
            return "redirect:/team/" + teamId + "/" + url + "/setting";
        }
        try {
            teamService.update(form, teamId);
        } catch(RuntimeException e) {
            log.error(e.getMessage());
            attr.addFlashAttribute("teamUpdateError", true);
            return "redirect:/team/" + teamId + "/" + url + "/setting";
        }
        attr.addFlashAttribute("teamUpdateSuccess", true);
        return "redirect:/team/" + teamId + "/" + url;
    }

    @PostMapping(path = "/team/{teamId}/{url}",params = "delete")
    public String teamDelete(@PathVariable String teamId,@PathVariable String url, RedirectAttributes attr){
        try {
            teamService.delete(teamId);
        } catch(RuntimeException e) {
            log.error(e.getMessage());
            attr.addFlashAttribute("teamDeleteError", true);
            return "redirect:/team/" + teamId + "/" + url + "/setting";
        }
        attr.addFlashAttribute("teamDeleteSuccess", true);
        return "redirect:/";
    }
}
