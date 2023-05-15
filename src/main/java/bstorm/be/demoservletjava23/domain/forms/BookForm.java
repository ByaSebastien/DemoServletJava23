package bstorm.be.demoservletjava23.domain.forms;


import bstorm.be.demoservletjava23.domain.entities.Author;
import bstorm.be.demoservletjava23.domain.entities.Book;
import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class BookForm {

    private String title;
    private String description;
    private Integer authorId;
    private AuthorForm author;

    public BookForm(String title, String description,Integer authorId) {
        this.title = title;
        this.description = description;
        this.authorId = authorId;
    }

    public BookForm(String title, String description,AuthorForm authorForm) {
        this.title = title;
        this.description = description;
        this.author = authorForm;
    }

    public Book toEntity(){

        Author author = getAuthor() == null ? null : getAuthor().toEntity();
        return Book.builder()
                .title(getTitle())
                .description(getDescription())
                .authorId(getAuthorId())
                .author(author)
                .build();
    }
}
