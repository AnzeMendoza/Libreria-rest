
package edu.sucho.libreriaweb.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserNotAutorizer {
    private String  error;
    private String  error_description;

}
