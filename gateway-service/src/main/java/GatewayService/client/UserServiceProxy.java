package GatewayService.client;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import GatewayService.dto.UserLoginObject;
import GatewayService.dto.UserRegisterObject;
import GatewayService.model.Roles;
import GatewayService.model.User;
import GatewayService.model.enums.ERoles;

@Service
public class UserServiceProxy implements IUserServiceProxy {

    RestTemplate restTemplate = new RestTemplate();
    private String baseUrl;
    private String userUrl;
    private String pplUrl;
    private String loginUrl;

    public UserServiceProxy(ConfigurableEnvironment env) {
        this.baseUrl = "http://localhost:8081/api";
        this.userUrl = baseUrl + "/user/{id}";
        this.pplUrl = baseUrl + "/user/";
        this.loginUrl = baseUrl + "/oauthtoken/";
    }

    @Override
    public List<User> getAll() {
         ResponseEntity<List<User>> response =
                restTemplate.exchange(pplUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {
                        });
        return response.getBody();
    }

    @Override
    public User register(UserRegisterObject user) {
        
        // ADDING DEFAULT USER ROLE EACH TIME
        Roles defaultUserRole = new Roles();
        defaultUserRole.setRole(ERoles.USER);
        List<Roles> roles = user.getRoles();
        roles.add(defaultUserRole);

        User createdUser = restTemplate.postForObject(pplUrl, user,User.class);
        return createdUser;
    }

    @Override
    public User get(Long id) {
        return restTemplate.getForObject(userUrl, User.class, id);
    }

    @Override
    public String login(UserLoginObject myUser) {
        String token = restTemplate.postForObject(loginUrl, myUser,String.class);
        return token;
        
    }
    
}
