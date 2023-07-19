package GatewayService.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import GatewayService.model.Roles;
import GatewayService.model.enums.ERoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterObject implements Serializable {

    @NotBlank
    String username;
    
    @NotBlank
    String password;

    @NotBlank
    @Email
    String email;

    List<Roles> roles;
    
    public UserRegisterObject() {
        roles = new ArrayList<>();
    }

    public void addRoles(ERoles erole) {
        Roles role = new Roles();
        role.setRole(erole);
        roles.add(role);
    }

}
