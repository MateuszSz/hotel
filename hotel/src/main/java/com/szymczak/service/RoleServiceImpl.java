package com.szymczak.service;

import com.szymczak.model.Role;
import com.szymczak.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mateu on 12.05.2017.
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void insertOrUpdate(Role role) {
        roleRepository.insertOrUpdate(role);
    }

    public Role display(int id) {
        return roleRepository.display(id);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
