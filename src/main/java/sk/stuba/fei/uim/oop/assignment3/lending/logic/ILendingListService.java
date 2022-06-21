package sk.stuba.fei.uim.oop.assignment3.lending.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lending.data.LendingList;

import java.util.List;

public interface ILendingListService {
    List<LendingList> getAll();

    LendingList create();

    LendingList getById(Long id) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    LendingList addToList(Long id, Long bookId) throws NotFoundException, IllegalOperationException;

    void deleteBookFromList(Long id,Long bookId) throws NotFoundException;

    void lendList(Long id) throws NotFoundException,IllegalOperationException;
}
