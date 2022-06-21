package sk.stuba.fei.uim.oop.assignment3.book.web.bodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String name;
    private String description;
    private Long author;
    private Long pages;
    private Long amount;
    private Long lendCount;

}
