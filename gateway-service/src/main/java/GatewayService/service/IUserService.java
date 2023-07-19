package GatewayService.service;

import java.util.List;

import GatewayService.dto.UserLoginObject;
import GatewayService.dto.UserRegisterObject;
import GatewayService.model.User;

public interface IUserService {
    public List<User> getAll();
    public User register(UserRegisterObject post);
    public User get(Long id);
    public String login(UserLoginObject user);
}

