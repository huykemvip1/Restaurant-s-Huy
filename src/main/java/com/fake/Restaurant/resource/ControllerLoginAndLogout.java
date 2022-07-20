package com.fake.Restaurant.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class ControllerLoginAndLogout {
    private int HAVE_ERROR=1;
    private int HAVE_NOT_ERROR=0;
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("message",HAVE_NOT_ERROR);
        return "login";
    }
    @GetMapping("/login-error")
    public String errorLogin(Model model){
        model.addAttribute("message",HAVE_ERROR);
        return "login";
    }
    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute("message",HAVE_NOT_ERROR);
        return "login";
    }
}
