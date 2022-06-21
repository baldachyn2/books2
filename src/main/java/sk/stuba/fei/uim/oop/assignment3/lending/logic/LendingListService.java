package sk.stuba.fei.uim.oop.assignment3.lending.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lending.data.ILendingListRepository;
import sk.stuba.fei.uim.oop.assignment3.lending.data.LendingList;

import java.util.List;


@Service
public class LendingListService implements ILendingListService {
    @Autowired
    private ILendingListRepository repository;

    @Autowired
    private IBookService bookService;

    @Override
    public List<LendingList> getAll() {
        return this.repository.findAll();
    }

    @Override
    public LendingList create() {
        return this.repository.save(new LendingList());
    }

    @Override
    public LendingList getById(Long id) throws NotFoundException {
        LendingList list = this.repository.findLendingListById(id);
        if (list == null) {
            throw new NotFoundException();
        }
        return list;
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        LendingList list = this.getById(id);
        for (int i = 0; i < list.getLendinglist().size(); i++) {
            list.getLendinglist().get(i).setLendCount(list.getLendinglist().get(i).getLendCount() - 1);
        }
        this.repository.delete(this.getById(id));
    }

    @Override
    public LendingList addToList(Long id, Long bookId) throws NotFoundException, IllegalOperationException {
        LendingList list = this.getLended(id);
        var bookInList = this.bookService.getById(bookId);
        if (list.getLendinglist().contains(bookInList)) {
            throw new IllegalOperationException();
        }
        list.getLendinglist().add(bookInList);
        return this.repository.save(list);
    }

    @Override
    public void deleteBookFromList(Long id, Long request) throws NotFoundException {
        LendingList list = this.getById(id);
        Book b = this.bookService.getById(request);
        list.getLendinglist().remove(b);
    }

    @Override
    public void lendList(Long id) throws NotFoundException, IllegalOperationException {
        LendingList list = this.getById(id);
        if (list == null) {
            throw new NotFoundException();
        }
        if (list.isLended()) {
            throw new IllegalOperationException();
        }
        list.setLended(true);
        for ( int i =0;i<list.getLendinglist().size();i++){
            list.getLendinglist().get(i).setLendCount(list.getLendinglist().get(i).getLendCount()+1);
        }
        this.repository.save(list);
    }


    private LendingList getLended(Long id) throws NotFoundException, IllegalOperationException {
        LendingList list = this.getById(id);
        if (list == null) {
            throw new NotFoundException();
        }
        if (list.isLended()) {
            throw new IllegalOperationException();
        }
        return list;
    }
}
