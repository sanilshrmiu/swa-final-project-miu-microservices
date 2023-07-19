package com.UserService.daos;

import java.util.Optional;

import com.UserService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserDao extends JpaRepository<User,Long>{

    Optional<User> findAllByUsernameAndPassword(String username, String password);
    Optional<User> findAllByUsername(String username);
}
