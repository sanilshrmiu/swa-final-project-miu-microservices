package com.UserService.daos;

import com.UserService.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.UserService.models.enums.ERoles;

@Component
public interface IRoleDao extends JpaRepository<Roles,Long> {
    
    public Roles findOneByRole(ERoles nameOfRole);
}
