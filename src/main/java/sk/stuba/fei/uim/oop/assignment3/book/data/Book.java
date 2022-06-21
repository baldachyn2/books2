package sk.stuba.fei.uim.oop.assignment3.book.data;

import lombok.*;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private Author author;
    private Long pages;
    private Long amount;
    private Long lendCount;
}
