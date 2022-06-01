package com.DevoirFinal.gestion_tickets_incidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DevoirFinal.gestion_tickets_incidents.model.Ticket;
import com.DevoirFinal.gestion_tickets_incidents.repository.TicketRepository;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public Ticket findById(Long id){
        return ticketRepository.getById(id);
    }

    public void save(Ticket ticket){
         ticketRepository.save(ticket);
    }

    public void delete(Ticket ticket){
        ticketRepository.delete(ticket);
    }

    public List<Ticket> findAllByDevelopeurIsNull(){
        return ticketRepository.findAllByDevelopeurIsNull();
    }

    public List<Ticket> findAllByDevelopeurEquals(String user){
        return ticketRepository.findAllByDevelopeur_UsernameEquals(user);
    }

    public List<Ticket> findAllByClient_UsernameEquals(String user){
        return ticketRepository.findAllByClient_UsernameEquals(user);
    }
//
    public List<Ticket> search1(String search){
        return ticketRepository.findByDescriptionOrNameOrEtatOrLogicielOrEnvironnementIsContainingAndDevelopeurIsNull(search,search,search,search,search);
    }
    public List<Ticket> search2(String search,String user){
        return ticketRepository.findByDescriptionOrNameOrEtatOrLogicielOrEnvironnementIsContainingAndDevelopeur_UsernameEquals(search,search,search,search,search,user);
    }
    public List<Ticket> search3(String search,String user){
        return ticketRepository.findByDescriptionOrNameOrEtatOrLogicielOrEnvironnementIsContainingAndClient_UsernameEquals(search,search,search,search,search,user);
    }

}
