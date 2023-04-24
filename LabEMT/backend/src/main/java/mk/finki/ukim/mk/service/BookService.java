package mk.finki.ukim.mk.service;

import mk.finki.ukim.mk.model.Book;
import mk.finki.ukim.mk.model.dto.BookDTO;
import java.util.List;
import java.util.Optional;


public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> create( Book b);

    void delete(Long bookId);

    Optional<Book> edit(Long bookId,BookDTO bookDto);

    Optional<Book> markAsTaken(Long bookId);

    Optional<Book> bookDtoToBook(BookDTO book);
}