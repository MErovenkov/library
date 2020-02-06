package com.library.controller;

import com.library.dto.PublisherDto;
import com.library.mapper.PublisherMapper;
import com.library.model.Publisher;
import com.library.service.interfaces.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private IPublisherService publisherService;

    @Autowired
    private PublisherMapper publisherMapper;

    //todo: admin
    @PostMapping("/")
    public PublisherDto createPublisher(@RequestBody PublisherDto publisherDto) {
        return this.publisherMapper.convertToDto(
                this.publisherService.createPublisher(
                        this.publisherMapper.convertToEntity(publisherDto)));
    }

    //todo: admin
    @PutMapping("/{idPublisher}")
    public PublisherDto updatePublisher(@PathVariable Integer idPublisher,
                                     @RequestBody PublisherDto publisherDto) {
        return this.publisherMapper.convertToDto(
                this.publisherService.updatePublisher(
                        idPublisher, this.publisherMapper.convertToEntity(publisherDto)));
    }

    //todo: admin
    @DeleteMapping("/{idPublisher}")
    public PublisherDto deletePublisherById(@PathVariable Integer idPublisher) {
        return this.publisherMapper.convertToDto(
                this.publisherService.deletePublisherById(idPublisher));
    }

    //todo: admin / user
    @GetMapping("/{idPublisher}")
    public PublisherDto findPublisherById(@PathVariable Integer idPublisher) {
        return this.publisherMapper.convertToDto(
                this.publisherService.findPublisherById(idPublisher));
    }

    //todo: admin / user
    @GetMapping("/name={namePublisher}")
    public PublisherDto findPublisherByName(@PathVariable String namePublisher) {
        return this.publisherMapper.convertToDto(
                this.publisherService.findPublisherByName(namePublisher));
    }

    //todo: admin / user
    @GetMapping("/")
    public List<PublisherDto> findPublishersList() {
        return this.publisherMapper.convertToListDto(this.publisherService.findPublishersList());
    }

}
