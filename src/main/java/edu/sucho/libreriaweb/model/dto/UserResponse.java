package edu.sucho.libreriaweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

}
