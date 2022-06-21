package sk.stuba.fei.uim.oop.assignment3.author.web.bodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {
    private Long id;
    private String name;
    private String surname;
    private List<Long> books;

    public AuthorResponse(Author a){
        this.id=a.getId();
        this.name=a.getName();
        this.surname=a.getSurname();
        this.books=a.getBook().stream().map(Book::getId).collect(Collectors.toList());
    }
}
