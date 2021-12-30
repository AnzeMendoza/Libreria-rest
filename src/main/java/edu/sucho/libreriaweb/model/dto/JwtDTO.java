package edu.sucho.libreriaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JwtDTO {
    private String date;
    private String jwt;
    private String ttl;

    public JwtDTO(String jwt) {
        date = new Date().toString();
        this.jwt=jwt;
        this.ttl = System.getenv().get("JWT_TTL");
    }
}
