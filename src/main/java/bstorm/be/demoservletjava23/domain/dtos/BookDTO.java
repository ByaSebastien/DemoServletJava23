package bstorm.be.demoservletjava23.domain.dtos;

import bstorm.be.demoservletjava23.domain.entities.Book;
import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class BookDTO {

    private Integer id;
    private String title;
    private String description;

    public BookDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public static BookDTO fromEntity(Book book){


        String description;

        if(book.getDescription().length() < 20){
            description = book.getDescription();
        }else {
            description = book.getDescription().substring(0,20) + "...";
        }

        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(description)
                .build();
    }
}
