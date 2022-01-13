package edu.sucho.libreriaweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

}

