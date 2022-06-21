package sk.stuba.fei.uim.oop.assignment3.lending.web.bodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.lending.data.LendingList;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LendingListResponse {
    private Long id;
    private List<BookResponse> lendingList;
    private boolean lended;

    public LendingListResponse(LendingList lendingList) {
        this.id= lendingList.getId();
        this.lendingList=lendingList.getLendinglist().stream().map(BookResponse::new).collect(Collectors.toList());
        this.lended=lendingList.isLended();
    }
}
