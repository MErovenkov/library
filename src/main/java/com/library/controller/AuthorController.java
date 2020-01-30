package com.library.controller;

import com.library.model.Author;
import com.library.service.interfaces.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private IAuthorService iAuthorService;

    @PostMapping
    public void createAuthor(@RequestBody Author author) {
        this.iAuthorService.createAuthor(author);
    }

    @DeleteMapping
    public void deleteAuthor(@RequestBody Author author) {
        this.iAuthorService.deleteAuthor(author);
    }

    @DeleteMapping("/{idAuthor}")
    public void deleteAuthorById(@PathVariable Integer idAuthor) {
        this.iAuthorService.deleteAuthorById(idAuthor);
    }

    @GetMapping("/{idAuthor}")
    public Author getAuthorById(@PathVariable Integer idAuthor) {
        return this.iAuthorService.getAuthorById(idAuthor);
    }

    @GetMapping("/")
    public List<Author> getAuthorList() {
        return this.iAuthorService.getAuthorList();
    }

    /// сортировки + update
        /// сортировка по алфавиту
}
