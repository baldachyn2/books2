package sk.stuba.fei.uim.oop.assignment3.book.web.bodies;

import lombok.Data;

@Data
public class BookRequestEdit {
    private String name;
    private String description;
    private Long author;
    private Long pages;
}
