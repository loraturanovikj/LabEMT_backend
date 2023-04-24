package mk.finki.ukim.mk.restcontroller;

import mk.finki.ukim.mk.model.enums.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryRestController {

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        System.out.println("CATEGORIES");
        return Arrays.stream(Category.values()).collect(Collectors.toList());
    }
}
