package edu.sucho.libreriaweb.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserNotAutorizer {
    private String  error;
    private String  error_description;

}
