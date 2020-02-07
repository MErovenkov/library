package com.library.controller;

import com.library.dto.AuthorDto;
import com.library.mapper.AuthorMapper;
import com.library.model.Genre;
import com.library.service.interfaces.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private AuthorMapper authorMapper;


    //todo: admin
    @PostMapping("/")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return this.authorMapper.convertToDto(
                this.authorService.createAuthor(
                        this.authorMapper.convertToEntity(authorDto)));
    }

    //todo: admin
    @PutMapping("/{idAuthor}")
    public AuthorDto updateAuthor(@PathVariable Integer idAuthor,
                                  @RequestBody AuthorDto authorDto) {
        return this.authorMapper.convertToDto(
                this.authorService.updateAuthor(
                        idAuthor, this.authorMapper.convertToEntity(authorDto)));
    }

    //todo: admin
    @PutMapping("/{idAuthor}/add-genre")
    public AuthorDto addGenreToAuthor(@PathVariable Integer idAuthor,
                                      @RequestParam Integer idGenre) {
        return this.authorMapper.convertToDto(
                this.authorService.addGenreToAuthor(idAuthor, idGenre));
    }

    @PutMapping("/{idAuthor}/delete-genre")
    public AuthorDto deleteGenreToAuthor(@PathVariable Integer idAuthor,
                                         @RequestParam(name = "id") Integer idGenre) {
        return this.authorMapper.convertToDto(
                this.authorService.deleteGenreToAuthor(idAuthor, idGenre));
    }

    //todo: admin
    @DeleteMapping("/{idAuthor}")
    public AuthorDto deleteAuthorById(@PathVariable Integer idAuthor) {
        return this.authorMapper.convertToDto(
                this.authorService.deleteAuthorById(idAuthor));
    }

    //todo: admin / user
    @GetMapping("/{idAuthor}")
    public AuthorDto findAuthorById(@PathVariable Integer idAuthor) {
        return this.authorMapper.convertToDto(
                authorService.findAuthorById(idAuthor));
    }

    //todo: подумать над тем, как лучше назвать запрос
    //todo: admin / user
    @GetMapping("/by-name")
    public AuthorDto findAuthorByFullName(@RequestParam(name = "surname") String surnameSearch,
                                          @RequestParam(name = "name") String nameSearch,
                                          @RequestParam(name = "patronymic") String patronymicSearch) {
        return this.authorMapper.convertToDto(
                this.authorService.findAuthorByFullName(surnameSearch, nameSearch, patronymicSearch));
    }

    //todo: admin / user
    @GetMapping("/by-genre")
    public List<AuthorDto> findAuthorsByGenre(@RequestParam(name = "id") Integer idGenre){
        return this.authorMapper.convertToListDto(
                this.authorService.findAuthorsByGenre(idGenre));
    }

    //todo: admin / user
    @GetMapping("/")
    public List<AuthorDto> findAuthorsList() {
        return this.authorMapper.convertToListDto(
                authorService.findAuthorsList());
    }
/*
    //todo: admin / user
    //todo: по surname
    public List<Author> findSortAuthorsList(SortingComparator sortingComparator) {
        return this.authorService.findSortAuthorsList(sortingComparator);
    }*/
}
