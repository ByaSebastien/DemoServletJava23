package bstorm.be.demoservletjava23.domain.forms;

import bstorm.be.demoservletjava23.domain.entities.Author;
import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class AuthorForm {

    private String firstname;

    private String lastname;

    private String pseudo;

    public Author toEntity(){

        return Author.builder()
                .firstname(getFirstname())
                .lastname(getLastname())
                .pseudo(getPseudo())
                .build();
    }
}
