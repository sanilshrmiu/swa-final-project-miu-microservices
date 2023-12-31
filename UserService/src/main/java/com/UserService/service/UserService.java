package com.UserService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.UserService.daos.IUserDao;
import com.UserService.exceptions.UserCreationException;
import com.UserService.models.Roles;
import com.UserService.models.User;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private IUserDao ud;
    @Autowired
    private RolesService roleServ;

    public List <User> getAllUsers() {
        return ud.findAll();
    }

    public Optional < User > getUserById(Long id) {
        return ud.findById(id);
    }

    public Optional < User > saveUser(User user) {

        List <Roles> roles = new ArrayList < > ();
        for (Roles userRole: user.getRoles()) {
            Roles existingRole = roleServ.findRoleByERole(userRole.getRole());
            if (existingRole != null) {
                roles.add(existingRole);
            }
        }
        user.setRoles(roles);
        String salt = BCrypt.gensalt();

        user.setPassword(BCrypt.hashpw(user.getPassword(), salt));
        try {
            User savedUser = ud.save(user);
            return Optional.of(savedUser);
            
        } catch (DataIntegrityViolationException e) {
            // TODO: handle exception
            throw new DataIntegrityViolationException("User already exists");
        }
        
    }

    public Optional < User > getUserByUsernameAndPassword(String username, String password) {
        return ud.findAllByUsernameAndPassword(username, password);
    }
}