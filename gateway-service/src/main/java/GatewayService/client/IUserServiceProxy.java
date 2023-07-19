package GatewayService.client;

import java.util.List;

import GatewayService.dto.UserLoginObject;
import GatewayService.dto.UserRegisterObject;
import GatewayService.model.User;

public interface IUserServiceProxy {
    public List<User> getAll();
    public User register(UserRegisterObject user);
    public User get(Long id);
    public String login(UserLoginObject user);
}

