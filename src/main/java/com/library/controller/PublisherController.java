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
    private IPublisherService iPublisherService;

    @PostMapping
    public void createPublisher(@RequestBody Publisher publisher) {
        this.iPublisherService.createPublisher(publisher);
    }

    @PutMapping("/{idPublisher}")
    public Publisher updatePublisher(@PathVariable Integer idPublisher,
                                     @RequestBody Publisher publisher) {
        return this.iPublisherService.updatePublisher(idPublisher, publisher);
    }

    @DeleteMapping("/{idPublisher}")
    public void deletePublisherById(@PathVariable Integer idPublisher) {
        this.iPublisherService.deletePublisherById(idPublisher);
    }

    @GetMapping("/{idPublisher}")
    public Publisher findPublisherById(@PathVariable Integer idPublisher) {
        return this.iPublisherService.findPublisherById(idPublisher);
    }

    @GetMapping("/{namePublisher}")
    public Publisher findPublisherByName(@PathVariable String namePublisher) {
        return this.iPublisherService.findPublisherByName(namePublisher);
    }

    @GetMapping("/")
    public List<Publisher> getPublisherList() {
        return this.iPublisherService.findPublisherList();
    }

}
