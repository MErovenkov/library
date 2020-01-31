package com.library.service.interfaces;

import com.library.model.Publisher;

import java.util.List;

public interface IPublisherService {

    void createPublisher(Publisher publisher);

    Publisher updatePublisher(Integer idPublisher, Publisher newDataPublisher);

    void deletePublisherById(Integer idPublisher);

    Publisher findPublisherById(Integer idPublisher);

    Publisher findPublisherByName(String namePublisher);

    List<Publisher> findPublisherList();
}
