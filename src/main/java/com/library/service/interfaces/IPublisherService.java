package com.library.service.interfaces;

import com.library.model.Publisher;

import java.util.List;

public interface IPublisherService {
    void createPublisher(Publisher publisher);

    void deletePublisherById(Integer idPublisher);

    Publisher getPublisherById(Integer idPublisher);

    List<Publisher> getPublisherList();
}
