package com.UserService.service;

import com.UserService.daos.IRoleDao;
import com.UserService.models.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserService.models.enums.ERoles;

@Service
public class RolesService {
    
    @Autowired
    IRoleDao roleDao;

    public Roles findRoleByERole(ERoles nameOfRole) {
        return roleDao.findOneByRole(nameOfRole);
    }
}
