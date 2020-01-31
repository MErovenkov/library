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
    private IGenreService iGenreService;

    @PostMapping
    public void createGenre(@RequestBody Genre genre) {
        this.iGenreService.createGenre(genre);
    }

    @PutMapping("/{idGenre}")
    public Genre updateGenre(@PathVariable Integer idGenre,
                                     @RequestBody Genre genre) {
        return this.iGenreService.updateGenre(idGenre, genre);
    }

    @DeleteMapping("/{idGenre}")
    public void deleteGenreById(@PathVariable Integer idGenre) {
        this.iGenreService.deleteGenreById(idGenre);
    }

    @GetMapping("/{idGenre}")
    public Genre getGenreById(@PathVariable Integer idGenre) {
        return this.iGenreService.findGenreById(idGenre);
    }

    @GetMapping("/{nameGenre}")
    public Genre findGenreByName(@PathVariable String nameGenre) {
        return this.iGenreService.findGenreByName(nameGenre);
    }

    @GetMapping("/")
    public List<Genre> getGenreList() {
        return this.iGenreService.findGenreList();
    }
}
