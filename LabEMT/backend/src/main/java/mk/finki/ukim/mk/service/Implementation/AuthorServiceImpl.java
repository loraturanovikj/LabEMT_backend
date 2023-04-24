package mk.finki.ukim.mk.service.Implementation;

import mk.finki.ukim.mk.model.Author;
import mk.finki.ukim.mk.model.Country;
import mk.finki.ukim.mk.model.dto.AuthorDTO;
import mk.finki.ukim.mk.model.exceptions.AuthorNotFound;
import mk.finki.ukim.mk.repository.AuthorRepository;
import mk.finki.ukim.mk.repository.CountryRepository;
import mk.finki.ukim.mk.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> create(Author a) {
        return Optional.of(authorRepository.save(a));
    }

    @Override
    public void delete(Long authorId) {
        Optional<Author> a = authorRepository.findById(authorId);
        if(a.isPresent())
            authorRepository.delete(a.get());
        else throw new AuthorNotFound(authorId);
    }

    @Override
    public Optional<Author> authorDtoToAuthor(AuthorDTO author) {
        Country c = countryRepository.findById(author.getCountryId())
                .orElseThrow(()->new EmptyStackException());
        Author b = null;
        if (author.getName() != null && author.getSurname()!= null)
            b = new Author(author.getName(),author.getSurname(),c);
        return Optional.of(b);

    }
}
