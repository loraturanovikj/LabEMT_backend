package mk.finki.ukim.mk.service;

import mk.finki.ukim.mk.model.Author;
import mk.finki.ukim.mk.model.dto.AuthorDTO;
import java.util.List;
import java.util.Optional;


public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> create( Author a);

    void delete(Long authorId);

    Optional<Author> authorDtoToAuthor(AuthorDTO author);

}
