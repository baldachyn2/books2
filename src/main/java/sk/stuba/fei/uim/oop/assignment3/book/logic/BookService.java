package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.IBookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequestEdit;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository repository;

    @Autowired
    private IAuthorService authorService;

    @Override
    public List<Book> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Book create(BookRequest request) throws NotFoundException {
        Book b = new Book();
        b.setName(request.getName());
        b.setDescription(request.getDescription());
        b.setPages(request.getPages());
        b.setAmount(request.getAmount());
        b.setLendCount(request.getLendCount());
        if (request.getAuthor() == null) {
            return this.repository.save(b);
        }
        if (request.getAuthor() != null) {
            Author author = this.authorService.getById(request.getAuthor());
            b.setAuthor(author);
            author.getBook().add(b);
        }
        return this.repository.save(b);
    }

        @Override
        public Book getById (Long id) throws NotFoundException {
            Book b = this.repository.getBookById(id);
            if (b == null) {
                throw new NotFoundException();
            }
            return b;
        }

        @Override
        public Book update (Long id, BookRequestEdit request) throws NotFoundException {
            Book b = this.repository.getBookById(id);
            if(b==null){
                throw new NotFoundException();
            }
            if (request.getName() != null) {
                b.setName(request.getName());
            }
            if (request.getDescription() != null) {
                b.setDescription(request.getDescription());
            }
            if (request.getAuthor() != 0) {
                b.setAuthor(this.authorService.getById(request.getAuthor()));
            }
            if (request.getPages() != 0 && request.getPages() != null) {
                b.setPages(request.getPages());
            }
            return this.repository.save(b);
        }

        @Override
        public void delete (Long id) throws NotFoundException {
            var b = this.getById(id);
            b.getAuthor().getBook().remove(b);
            this.repository.delete(b);
        }

        @Override
        public Long getAmount (Long id) throws NotFoundException {
            if (this.repository.getBookById(id) == null) {
                throw new NotFoundException();
            }
            return this.repository.getBookById(id).getAmount();
        }

        @Override
        public Long addAmount (Long id, Long add) throws NotFoundException {
            Book b = this.repository.getBookById(id);
            if (b == null) {
                throw new NotFoundException();
            }
            b.setAmount(b.getAmount() + add);
            this.repository.save(b);
            return b.getAmount();
        }

        @Override
        public Long getLendCount (Long id) throws NotFoundException {
            Book b = this.repository.getBookById(id);
            if (b == null) {
                throw new NotFoundException();
            }
            return b.getLendCount();
        }
    }
