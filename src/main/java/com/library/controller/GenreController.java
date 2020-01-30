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


    /// ?delete @RequestBody...?

    @DeleteMapping("/{idGenre}")
    public void deleteGenreById(@PathVariable Integer idGenre) {
        this.iGenreService.deleteGenreById(idGenre);
    }

    @GetMapping("/{idGenre}")
    public Genre getGenreById(@PathVariable Integer idGenre) {
        return this.iGenreService.getGenreById(idGenre);
    }

    @GetMapping("/")
    public List<Genre> getGenreList() {
        return this.iGenreService.getGenreList();
    }


    //вывод по имени жанра
    /// сортировки + update???
}
