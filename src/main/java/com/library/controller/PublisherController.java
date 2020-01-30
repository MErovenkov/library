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

    /// ?delete @RequestBody...?

    @DeleteMapping("/{idPublishing}")
    public void deletePublisherById(@PathVariable Integer idPublisher) {
        this.iPublisherService.deletePublisherById(idPublisher);
    }

    @GetMapping("/{idPublishing}")
    public Publisher getPublisherById(@PathVariable Integer idPublisher) {
        return this.iPublisherService.getPublisherById(idPublisher);
    }

    @GetMapping("/")
    public List<Publisher> getPublisherList() {
        return this.iPublisherService.getPublisherList();
    }


    /// сортировки + update??
}
