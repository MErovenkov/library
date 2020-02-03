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
    private IAuthorService authorService;

    @PostMapping("/")
    public Author createAuthor(@RequestBody Author author) {
         return this.authorService.createAuthor(author);
    }

    @PutMapping("/{idAuthor}")
    public Author updateAuthor(@PathVariable Integer idAuthor,
                             @RequestBody Author author) {
        return this.authorService.updateAuthor(idAuthor, author);
    }

    //todo: подумать над тем, как лучше назвать запрос
    @PutMapping("/{idAuthor}/add-genre")
    public Author addGenreToAuthor(@PathVariable Integer idAuthor,
                                   @RequestParam Integer idGenre) {
        return this.authorService.addGenreToAuthor(idAuthor, idGenre);
    }

    @DeleteMapping("/{idAuthor}")
    public Author deleteAuthorById(@PathVariable Integer idAuthor) {
        return this.authorService.deleteAuthorById(idAuthor);
    }

    @GetMapping("/{idAuthor}")
    public Author getAuthorById(@PathVariable Integer idAuthor) {
        return this.authorService.findAuthorById(idAuthor);
    }

    //todo: подумать над тем, как лучше назвать запрос
    @GetMapping("/FullName")
    public Author findAuthorByFullName(@RequestBody Author author) {
        return this.authorService.findAuthorByFullName(author);
    }

    @GetMapping("/")
    public List<Author> getAuthorList() {
        return this.authorService.findAuthorList();
    }



}
