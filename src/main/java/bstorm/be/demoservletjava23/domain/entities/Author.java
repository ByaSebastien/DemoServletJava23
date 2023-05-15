package bstorm.be.demoservletjava23.domain.entities;


import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Author {

    private Integer id;

    private String firstname;

    private String lastname;

    private String pseudo;
}
