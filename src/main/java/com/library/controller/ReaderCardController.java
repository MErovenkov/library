package com.library.controller;

import com.library.dto.ReaderCardDto;
import com.library.mapper.ReaderCardMapper;
import com.library.service.interfaces.IReaderCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers-cards")
public class ReaderCardController {

    @Autowired
    private IReaderCardService readerCardService;

    @Autowired
    private ReaderCardMapper readerCardMapper;

    //todo: admin
    @PutMapping("/{idReaderCard}")
    public ReaderCardDto updateReaderCard(@PathVariable Integer idReaderCard,
                                          @RequestBody ReaderCardDto readerCardDto) {
        return this.readerCardMapper.convertToDto(
                this.readerCardService.updateReaderCard(idReaderCard,
                        this.readerCardMapper.convertToEntity(readerCardDto)));
    }

    //todo: admin/ user?id привязка
    @GetMapping("/{idReaderCard}")
    public ReaderCardDto findReaderCardById(@PathVariable Integer idReaderCard) {
        return this.readerCardMapper.convertToDto(
                readerCardService.findReaderCardById(idReaderCard));
    }

    //todo: admin
    //todo: подумать над тем, как лучше назвать запрос
    @GetMapping("/by-name")
    public ReaderCardDto findReaderCardByFullName(@RequestParam(name = "Surname") String surnameSearch,
                                                  @RequestParam(name = "name") String nameSearch,
                                                  @RequestParam(name = "patronymic") String patronymicSearch) {
        return this.readerCardMapper.convertToDto(
                this.readerCardService.findReaderCardByFullName(surnameSearch, nameSearch, patronymicSearch));
    }

    //todo: admin
    @GetMapping("/")
    public List<ReaderCardDto> findReadersCardsList() {
        return this.readerCardMapper.convertToListDto(
                readerCardService.findReadersCardsList());
    }
}
