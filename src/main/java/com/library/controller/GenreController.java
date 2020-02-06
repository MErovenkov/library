package com.library.controller;

import com.library.dto.GenreDto;
import com.library.mapper.GenreMapper;
import com.library.service.interfaces.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private IGenreService genreService;

    @Autowired
    private GenreMapper genreMapper;

    //todo: admin
    @PostMapping("/")
    public GenreDto createGenre(@RequestBody GenreDto genreDto) {
        return this.genreMapper.convertToDto(
                this.genreService.createGenre(
                        this.genreMapper.convertToEntity(genreDto)));
    }

    //todo: admin
    @PutMapping("/{idGenre}")
    public GenreDto updateGenre(@PathVariable Integer idGenre,
                                     @RequestBody GenreDto genreDto) {
        return this.genreMapper.convertToDto(
                this.genreService.updateGenre(
                        idGenre, this.genreMapper.convertToEntity(genreDto)));
    }

    //todo: admin / user
    @DeleteMapping("/{idGenre}")
    public GenreDto deleteGenreById(@PathVariable Integer idGenre) {
        return this.genreMapper.convertToDto(genreService.deleteGenreById(idGenre));
    }

    //todo: admin / user
    @GetMapping("/{idGenre}")
    public GenreDto findGenreById(@PathVariable Integer idGenre) {
        return this.genreMapper.convertToDto(this.genreService.findGenreById(idGenre));
    }

    //todo: admin / user
    @GetMapping("/name={nameGenre}")
    public GenreDto findGenreByName(@PathVariable String nameGenre) {
        return this.genreMapper.convertToDto(this.genreService.findGenreByName(nameGenre));
    }

    //todo: admin / user
    @GetMapping("/")
    public List<GenreDto> findGenresList() {
        return this.genreMapper.convertToListDto(genreService.findGenresList());
    }

    //todo: admin / user
    //todo: сортировка по алфавиту?


}
