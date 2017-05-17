package com.szymczak.repository;

import com.szymczak.model.Role;


/**
 * Created by mateu on 12.05.2017.
 */
public interface RoleRepository {
    void insertOrUpdate(Role role);

    Role display(int id);

    Role findByName(String name);
}
