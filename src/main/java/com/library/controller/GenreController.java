package com.library.controller;

import com.library.model.Genre;
import com.library.service.interfaces.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private IGenreService genreService;

    @PostMapping("/")
    public Genre createGenre(@RequestBody Genre genre) {
        return this.genreService.createGenre(genre);
    }

    @PutMapping("/{idGenre}")
    public Genre updateGenre(@PathVariable Integer idGenre,
                                     @RequestBody Genre genre) {
        return this.genreService.updateGenre(idGenre, genre);
    }

    @DeleteMapping("/{idGenre}")
    public Genre deleteGenreById(@PathVariable Integer idGenre) {
        return this.genreService.deleteGenreById(idGenre);
    }

    @GetMapping("/{idGenre}")
    public Genre getGenreById(@PathVariable Integer idGenre) {
        return this.genreService.findGenreById(idGenre);
    }

    //todo:
    @GetMapping("/name/{nameGenre}")
    public Genre findGenreByName(@PathVariable String nameGenre) {
        return this.genreService.findGenreByName(nameGenre);
    }

    @GetMapping("/")
    public List<Genre> getGenreList() {
        return this.genreService.findGenreList();
    }
}
