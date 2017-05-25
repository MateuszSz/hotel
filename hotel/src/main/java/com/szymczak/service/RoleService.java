package com.szymczak.service;

import com.szymczak.model.Role;
import com.szymczak.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public void insertOrUpdate(Role role) {
        roleRepository.save(role);
    }

    public Role display(int id) {
        return roleRepository.findOne(id);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
