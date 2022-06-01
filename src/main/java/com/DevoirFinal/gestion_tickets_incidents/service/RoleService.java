package com.DevoirFinal.gestion_tickets_incidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DevoirFinal.gestion_tickets_incidents.model.Role;
import com.DevoirFinal.gestion_tickets_incidents.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public Role findFirstByNom(String nom){
       return repository.findFirstByNom(nom);
    }
}
