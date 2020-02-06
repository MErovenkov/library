package com.library.service;

import com.library.dao.interfaces.IReaderCardDao;
import com.library.model.Entry;
import com.library.model.ReaderCard;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IReaderCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReaderCardService implements IReaderCardService {

    @Autowired
    private IReaderCardDao readerCardDao;

    @Override
    public ReaderCard updateReaderCard(Integer idReaderCard, ReaderCard newDataReaderCard) {
        return null;
    }

    @Override
    public ReaderCard findReaderCardById(Integer idReaderCard) {
        return null;
    }

    @Override
    public ReaderCard findReaderCardByFullName(String surnameSearch, String nameSearch, String patronymicSearch) {
            return null;
    }

    @Override
    public List<ReaderCard> findReadersCardsList() {
        return null;
    }

    @Override
    public List<ReaderCard> findSortReadersCardsList(SortingComparator sortingComparator) {
        return null;
    }

    @Override
    public List<Entry> findEntriesByReaderCard(Integer idReaderCard) {
        return null;
    }
}
