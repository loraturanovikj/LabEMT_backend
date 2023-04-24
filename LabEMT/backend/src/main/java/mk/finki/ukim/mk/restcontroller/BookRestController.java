package mk.finki.ukim.mk.restcontroller;

import mk.finki.ukim.mk.model.Book;
import mk.finki.ukim.mk.model.dto.BookDTO;
import mk.finki.ukim.mk.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/api/books
//http://localhost:8080/api/books/1
//http://localhost:8080/api/books/markAsTaken/1
// http://localhost:8080/api/books/delete/1
//http://localhost:8080/api/books/add
//http://localhost:8080/api/books/edit/1

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/","/books"})
    public List<Book> getAllBooks(){
        return this.bookService.findAll();
    }

    @GetMapping({"/books/{id}"})
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/books/markAsTaken/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id) {
        return this.bookService.markAsTaken(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/books/add")
    public ResponseEntity<Book> save(@RequestBody BookDTO bookDto) {
        return this.bookService.create(bookService.bookDtoToBook(bookDto).get())
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/books/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, @RequestBody BookDTO bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.delete(id);
        if(this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
