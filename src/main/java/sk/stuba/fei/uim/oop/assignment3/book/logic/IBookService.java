package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequestEdit;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface IBookService {
    List<Book> getAll();
    Book create(BookRequest request) throws NotFoundException;
   Book getById(Long id) throws NotFoundException;

    Book update(Long id, BookRequestEdit request) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    Long getAmount(Long id) throws NotFoundException;

    Long addAmount(Long id, Long add) throws NotFoundException;

    Long getLendCount(Long id) throws NotFoundException;

}
