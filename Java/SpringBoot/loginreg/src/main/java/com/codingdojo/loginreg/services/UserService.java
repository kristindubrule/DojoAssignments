package com.codingdojo.loginreg.services;

import com.codingdojo.loginreg.repositories.RoleRepository;
import com.codingdojo.loginreg.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.codingdojo.loginreg.models.*;
import java.util.Date;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    // 2
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }

    // 3
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateLastLoginDateForUserByName(String username) {
        User user = userRepository.findByUsername(username);
        user.setLastSignin(new Date());
        userRepository.save(user);
    }
}
