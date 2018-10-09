package com.herokuapp.kashikari.controller;

import com.herokuapp.kashikari.form.LoanCreateForm;
import com.herokuapp.kashikari.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping(path = "/team/{teamId}/{url}/loan")
    public String loanCreate(@PathVariable String teamId, @PathVariable String url, @Validated @ModelAttribute LoanCreateForm form,
                               BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            attr.addFlashAttribute("loanCreateError", true);
            return "redirect:/team/" + teamId + "/" + url;
        }
        try {
            loanService.create(form);
        } catch(RuntimeException e) {
            log.error(e.getMessage());
            attr.addFlashAttribute("loanCreateError", true);
            return "redirect:/team/" + teamId + "/" + url;
        }
        attr.addFlashAttribute("loanCreateSuccess", true);
        return "redirect:/team/" + teamId + "/" + url;
    }

    @PostMapping(path = "/team/{teamId}/{url}/loan/{loanId}",params = "delete")
    public String loanDelete(@PathVariable String teamId,@PathVariable String url,@PathVariable String loanId, RedirectAttributes attr){
        try {
            loanService.delete(loanId);
        } catch(RuntimeException e) {
            log.error(e.getMessage());
            attr.addFlashAttribute("loanDeleteError", true);
            return "redirect:/team/" + teamId + "/" + url;
        }
        attr.addFlashAttribute("loanDeleteSuccess", true);
        return "redirect:/team/" + teamId + "/" + url;
    }
}
