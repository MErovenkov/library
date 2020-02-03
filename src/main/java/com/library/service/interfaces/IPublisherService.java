package com.library.service.interfaces;

import com.library.model.Publisher;

import java.util.List;

public interface IPublisherService {

    Publisher createPublisher(Publisher publisher);

    Publisher updatePublisher(Integer idPublisher, Publisher newDataPublisher);

    Publisher deletePublisherById(Integer idPublisher);

    Publisher findPublisherById(Integer idPublisher);

    Publisher findPublisherByName(String namePublisher);

    List<Publisher> findPublisherList();
}
