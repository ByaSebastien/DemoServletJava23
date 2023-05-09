package bstorm.be.demoservletjava23.domain.dtos;

import bstorm.be.demoservletjava23.domain.entities.User;
import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class ConnectedUserDTO {

    private Integer id;

    private String username;

    private String email;

    public static ConnectedUserDTO fromEntity(User user){

        return ConnectedUserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
