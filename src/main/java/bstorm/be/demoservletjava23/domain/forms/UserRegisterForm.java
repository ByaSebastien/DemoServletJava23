package bstorm.be.demoservletjava23.domain.forms;

import bstorm.be.demoservletjava23.domain.entities.User;
import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class UserRegisterForm {

    private String username;

    private String email;

    private String password;

    private String confirmPassword;

    public User toEntity(){

        return User.builder()
                .username(getUsername())
                .email(getEmail())
                .password(getPassword())
                .build();
    }
}
