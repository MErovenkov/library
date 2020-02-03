package com.library.controller;

import com.library.model.Publisher;
import com.library.service.interfaces.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishing")
public class PublisherController {

    @Autowired
    private IPublisherService publisherService;

    @PostMapping("/")
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return this.publisherService.createPublisher(publisher);
    }

    @PutMapping("/{idPublisher}")
    public Publisher updatePublisher(@PathVariable Integer idPublisher,
                                     @RequestBody Publisher publisher) {
        return this.publisherService.updatePublisher(idPublisher, publisher);
    }

    @DeleteMapping("/{idPublisher}")
    public Publisher deletePublisherById(@PathVariable Integer idPublisher) {
        return this.publisherService.deletePublisherById(idPublisher);
    }

    @GetMapping("/{idPublisher}")
    public Publisher findPublisherById(@PathVariable Integer idPublisher) {
        return this.publisherService.findPublisherById(idPublisher);
    }

    //todo:
    @GetMapping("/name/{namePublisher}")
    public Publisher findPublisherByName(@PathVariable String namePublisher) {
        return this.publisherService.findPublisherByName(namePublisher);
    }

    @GetMapping("/")
    public List<Publisher> getPublisherList() {
        return this.publisherService.findPublisherList();
    }

}
