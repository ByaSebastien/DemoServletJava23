package bstorm.be.demoservletjava23.domain.entities;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter @EqualsAndHashCode
public class Book {

    private Integer id;
    private String title;
    private String description;
    private Integer authorId;
    private Author author;

    public Book(String title, String description,Integer authorId) {
        this.title = title;
        this.description = description;
        this.authorId = authorId;
    }

    public Book(String title, String description,Author author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }
}
