package edu.sucho.libreriaweb.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Getter
@Setter
public class JwtDTO {
    private String date = new Date().toString();
    private String jwt;
    @Value("${security.jwt.ttlMillis}")
    private String ttl;

}
