package edu.sucho.libreriaweb.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


@Getter
@Setter
public class UnauthorizedUserDTO {

    @Value("access_denied")
    private String  error;
    @Value("Unauthorized")
    private String  error_description;

}
