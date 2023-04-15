package com.project.examserver.service;

import com.project.examserver.domain.Role;
import com.project.examserver.repository.RoleRepository;
import com.project.examserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role createRole(Role role){
        return roleRepository.save(role);
    }
}
