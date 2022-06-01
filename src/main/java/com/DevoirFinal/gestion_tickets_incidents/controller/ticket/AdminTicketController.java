package com.DevoirFinal.gestion_tickets_incidents.controller.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.DevoirFinal.gestion_tickets_incidents.model.Role;
import com.DevoirFinal.gestion_tickets_incidents.model.Ticket;
import com.DevoirFinal.gestion_tickets_incidents.model.User;
import com.DevoirFinal.gestion_tickets_incidents.service.RoleService;
import com.DevoirFinal.gestion_tickets_incidents.service.TicketService;
import com.DevoirFinal.gestion_tickets_incidents.service.UserService;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class AdminTicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public String tickets(Model model, Principal principal) {
        List<Ticket> data;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Role_Administrateur"))) {
            data = ticketService.findAllByDevelopeurIsNull();
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Role_Développeur"))) {
            data = ticketService.findAllByDevelopeurEquals(principal.getName());
        } else {
            data = ticketService.findAllByClient_UsernameEquals(principal.getName());
        }

        List<User> devs = userService.findFirstByRolesContains(roleService.findFirstByNom("Role_Développeur"));
        model.addAttribute("data", data);
        model.addAttribute("devs", devs);

        return "tickets/index";
    }

    @PreAuthorize("hasAuthority('Role_Client')")
    @GetMapping("ajouter")
    public String ajouterticket(Model model) {
        Ticket data = new Ticket();
        model.addAttribute("data", data);
        return "tickets/add";
    }

    @PreAuthorize("hasAuthority('Role_Client')")
    @PostMapping("ajouter")
    public String ajouterticketclick(@ModelAttribute("data") Ticket data, @AuthenticationPrincipal User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        data.setClient((userService.findByUsername(auth.getName())));
        data.setEtat("ouverture");
        ticketService.save(data);
        return "redirect:/tickets";
    }

    @PreAuthorize("hasAuthority('Role_Administrateur')")
    @PostMapping("supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        ticketService.delete(ticketService.findById(id));
        return "redirect:/tickets";
    }

    @PreAuthorize("hasAnyAuthority('Role_Administrateur','Role_Développeur')")
    @PostMapping("modifier/{id}/{field}/{newvalue}")
    @ResponseBody
    public String modifier(@PathVariable Long id, @PathVariable String field, @PathVariable String newvalue) {
        Ticket ticket = ticketService.findById(id);
        if (field.equals("etat"))
            ticket.setEtat(newvalue);
        else if (field.equals("developer"))
            ticket.setDevelopeur(userService.getById(Long.valueOf(newvalue)));
        ticketService.save(ticket);
        return "Bien Modifier ";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("search")
    public String ticketssearch(Model model, Principal principal, @RequestParam(name = "text",required = false) String text) {
        System.out.println("searchhh " + text);
        List<Ticket> data;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Role_Administrateur"))) {
            data = ticketService.search1(text);
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Role_Développeur"))) {
            data = ticketService.search2(text, principal.getName());
        } else {
            data = ticketService.search3(text, principal.getName());
        }

        List<User> devs = userService.findFirstByRolesContains(roleService.findFirstByNom("Role_Développeur"));
        model.addAttribute("data", data);
        model.addAttribute("devs", devs);
        model.addAttribute("searchtext", "Recherche : " + text);
        return "tickets/index";
    }
}
