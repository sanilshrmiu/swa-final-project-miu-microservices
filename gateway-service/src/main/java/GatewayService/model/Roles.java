package GatewayService.model;

import GatewayService.model.enums.ERoles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class Roles {

    Long id;

    @Enumerated(EnumType.STRING)
    ERoles role = ERoles.STUDENT;

}