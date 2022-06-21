package sk.stuba.fei.uim.oop.assignment3.author.data;


import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    @OneToMany(orphanRemoval = true)
    private List<Book> book;

    public Author() {
        this.book=new ArrayList<>();
    }
}
