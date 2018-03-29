package com.codingdojo.events.services;

import com.codingdojo.events.repositories.RoleRepository;
import com.codingdojo.events.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.codingdojo.events.models.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    public void saveUserWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    // 3
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Boolean noAdmins() {
        return !userRepository.existsByRolesContains(roleRepository.findFirstByName("ROLE_ADMIN"));
    }

    public Boolean isAdmin(User user) {
        return userRepository.existsUserByIdEqualsAndRolesContains(user.getId(), roleRepository.findFirstByName("ROLE_ADMIN"));
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public HashMap<Long,Boolean> admins() {
        List<Object[]> adminsObj = userRepository.admins();
        HashMap<Long,Boolean> adminsMap = new HashMap<>();
        for (Object[] obj : adminsObj) {
            adminsMap.put((Long)obj[0],true);
        }
        return adminsMap;
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public void makeUserAdmin(Long userId) {
        User user = findById(userId);
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }
}
