package com.DevoirFinal.gestion_tickets_incidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.DevoirFinal.gestion_tickets_incidents.model.Role;
import com.DevoirFinal.gestion_tickets_incidents.model.User;
import com.DevoirFinal.gestion_tickets_incidents.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    public List<User> findFirstByRolesContains(Role role){
       return userRepository.findFirstByRolesContains(role);
    }

    public User getById(Long id){
        return userRepository.getById(id);
    }

    public User findByUsername(String name){
        return userRepository.findByUsername(name);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        for (Role r : user.getRoles())
            System.out.println("Role:" + r.getNom());
        return new MyUserDetails(user);
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                user.getRoles().stream().map(r -> new SimpleGrantedAuthority( r.getNom())).collect(Collectors.toList()));


    }


}
