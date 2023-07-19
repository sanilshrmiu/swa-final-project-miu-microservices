package GatewayService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GatewayService.client.IUserServiceProxy;
import GatewayService.dto.UserLoginObject;
import GatewayService.dto.UserRegisterObject;
import GatewayService.model.User;

@Service
public class UserService implements IUserService {


    private final IUserServiceProxy userServiceProxy;

    public UserService(@Autowired IUserServiceProxy userServiceProxy) {
        this.userServiceProxy = userServiceProxy;
    }

    @Override
    public List < User > getAll() {
        return userServiceProxy.getAll();
    }

    @Override
    public User register(UserRegisterObject user) {
        return userServiceProxy.register(user);
    }

    @Override
    public User get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public String login(UserLoginObject user) {
        return userServiceProxy.login(user);       
    }

}