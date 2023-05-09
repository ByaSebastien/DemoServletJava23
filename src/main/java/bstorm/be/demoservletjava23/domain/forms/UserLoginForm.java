package bstorm.be.demoservletjava23.domain.forms;

import bstorm.be.demoservletjava23.domain.entities.User;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class UserLoginForm {

    private String login;

    private String password;

    public User toEntity(){

        return User.builder()
                .username(this.getLogin())
                .password(this.getPassword())
                .build();
    }
}
