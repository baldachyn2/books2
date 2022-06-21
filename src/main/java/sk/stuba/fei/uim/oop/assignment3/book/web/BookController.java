package sk.stuba.fei.uim.oop.assignment3.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookAmount;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequestEdit;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService service;

    @GetMapping()
    public List<BookResponse> getAllBooks(){
        return this.service.getAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest body) throws NotFoundException {
        return new ResponseEntity<>(new BookResponse(this.service.create(body)), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable("id")Long id) throws NotFoundException{
        return new BookResponse(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable("id") Long id, @RequestBody BookRequestEdit body) throws NotFoundException{
        return new BookResponse(this.service.update(id,body));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Long id) throws NotFoundException {
        this.service.delete(id);
    }

    @GetMapping("/{id}/amount")
    public BookAmount getAmount(@PathVariable("id")Long id) throws NotFoundException{
        return new BookAmount(this.service.getAmount(id));
    }

    @PostMapping("/{id}/amount")
    public BookAmount addAmount(@PathVariable("id") Long id, @RequestBody BookAmount body) throws NotFoundException{
        return new BookAmount(this.service.addAmount(id,body.getAmount()));
    }

    @GetMapping("/{id}/lendCount")
    public BookAmount getLendCount(@PathVariable("id")Long id) throws NotFoundException{
        return new BookAmount(this.service.getLendCount(id));
    }
}
