package sk.stuba.fei.uim.oop.assignment3.book.web.bodies;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

@Data
public class BookResponse {
    private Long id;
    private String name;
    private String description;
    private Long author;
    private Long pages;
    private Long amount;
    private Long lendCount;

    public BookResponse(Book b) {
        this.id=b.getId();
        this.name = b.getName();
        this.description = b.getDescription();
        if (b.getAuthor()!=null){
            this.author=b.getAuthor().getId();
        }
        this.pages = b.getPages();
        this.amount = b.getAmount();
        this.lendCount = b.getLendCount();
    }
}
