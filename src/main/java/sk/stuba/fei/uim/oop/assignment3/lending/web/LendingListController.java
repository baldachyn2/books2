package sk.stuba.fei.uim.oop.assignment3.lending.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lending.logic.ILendingListService;
import sk.stuba.fei.uim.oop.assignment3.lending.web.bodies.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.lending.web.bodies.LendingListResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class LendingListController {
    @Autowired
    private ILendingListService service;

    @GetMapping()
    public List<LendingListResponse> getAllLists(){
        return this.service.getAll().stream().map(LendingListResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<LendingListResponse> addLendingList() {
        return new ResponseEntity<>(new LendingListResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping( "/{id}")
    public LendingListResponse getLendingList(@PathVariable("id") Long id) throws NotFoundException {
        return new LendingListResponse(this.service.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteList(@PathVariable("id") Long id) throws NotFoundException {
        this.service.delete(id);
    }

    @PostMapping("/{id}/add")
    public LendingListResponse addToList(@PathVariable("id") Long id, @RequestBody BookIdRequest bookId) throws NotFoundException, IllegalOperationException {
        return new LendingListResponse(this.service.addToList(id, bookId.getId()));
    }

    @DeleteMapping( "/{id}/remove")
    public void deleteBook(@PathVariable("id") Long id,@RequestBody BookIdRequest request) throws NotFoundException {
        this.service.deleteBookFromList(id,request.getId());
    }

    @GetMapping("/{id}/lend")
    public void  lendList(@PathVariable("id") Long id) throws NotFoundException,IllegalOperationException{
        this.service.lendList(id);
    }
}
