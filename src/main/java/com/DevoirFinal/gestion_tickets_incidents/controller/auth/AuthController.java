package com.DevoirFinal.gestion_tickets_incidents.controller.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Controller
@RequestMapping("/")
public class AuthController {

    @GetMapping(value = "/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public String succeslogin(HttpServletRequest request) {

        return "redirect:/tickets";
    }
}
