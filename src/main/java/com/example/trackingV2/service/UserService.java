package com.example.trackingV2.service;

import com.example.trackingV2.model.Role;
import com.example.trackingV2.model.User;
import com.example.trackingV2.repository.RoleRepository;
import com.example.trackingV2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public User save(User user) {
        for (Role role : user.getRoles()) {
            if (role.getId() == null) {
                roleService.save(role);
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User ConvertFromOptionalToUser (Optional<User> user) {
        if(user.isPresent()) return user.get();
        return null;
    }

    public String getLastNameAndFirstLetterOfName(User user) {
        return user.getLastName() + " " + user.getFirstName().charAt(0);
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ConvertFromOptionalToUser(findByUsername(auth.getName()));
    }

    public String getCurrentUserRole(User user) {
        Set<Role> currentUserRole = user.getRoles();
        String nameRole ="";
        for (Role role : currentUserRole) {
            nameRole = role.getName();
        }
        return nameRole;
    }


    public List<User> getAllUsersWithSpecificRole(Set<Role> roles) {
        String stringRole = roles.iterator().next().getName().replace("_HEAD", "");
        Role roleWithoutHEAD = roleService.findRoleByName(stringRole);
        Set<Role> rolesWithoutHEAD = Set.of(roleWithoutHEAD);
        List<User> users = userRepository.findAllUserByRoles(rolesWithoutHEAD);
        users.add(getCurrentUser());
        return users;
        }

    }

