package sk.stuba.fei.uim.oop.assignment3.author.web.bodies;

import lombok.Data;

@Data
public class AuthorRequest {
    private String name;
    private String surname;
}