package com.DevoirFinal.gestion_tickets_incidents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DevoirFinal.gestion_tickets_incidents.model.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findFirstByNom(String nom);
}
