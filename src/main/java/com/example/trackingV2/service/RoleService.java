package com.example.trackingV2.service;

import com.example.trackingV2.model.Role;
import com.example.trackingV2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public boolean checkIfRoleIsHead(Set<Role> roles) {
        String stringRole = roles.iterator().next().getName();
        if(stringRole.contains("_HEAD")) {
            System.out.println("РОЛЬ СОДЕРЖИТ HEAD");
            return true;
        }
        System.out.println("РОЛЬ НЕ СОДЕРЖИТ HEAD");
        return false;
    }
}
