package mk.finki.ukim.mk.restcontroller;

import mk.finki.ukim.mk.model.Author;
import mk.finki.ukim.mk.model.dto.AuthorDTO;
import mk.finki.ukim.mk.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/api/authors
//http://localhost:8080/api/authors/1
//http://localhost:8080/api/authors/delete/1
//http://localhost:8080/api/authors/add

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping({"/authors"})
    public List<Author> getAllAuthors() {
        return this.authorService.findAll();
    }

    @GetMapping({"/authors/{id}"})
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        return this.authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/authors/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDTO authorDto) {
        return this.authorService.create(authorService.authorDtoToAuthor(authorDto).get())
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/authors/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.authorService.delete(id);
        if (this.authorService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
