package com.elite.customer_app.service.impl;

import com.elite.customer_app.model.Role;
import com.elite.customer_app.repository.RoleRepository;
import com.elite.customer_app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return null;
    }

}
