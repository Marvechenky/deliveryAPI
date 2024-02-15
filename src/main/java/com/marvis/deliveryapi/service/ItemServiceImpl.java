package com.marvis.deliveryapi.service;

import com.marvis.deliveryapi.data.dtos.request.ItemRequest;
import com.marvis.deliveryapi.data.model.Box;
import com.marvis.deliveryapi.data.model.Item;
import com.marvis.deliveryapi.exception.BoxNotFoundException;
import com.marvis.deliveryapi.repository.BoxRepository;
import com.marvis.deliveryapi.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final BoxRepository boxRepository;

    @Override
    public void saveItem(Item item, String txref) {
        Box box = boxRepository.findByTxrefIgnoreCase(txref)
                .orElseThrow(() ->
                        new BoxNotFoundException(String.format("Box with %s not found", txref)));
        item.setBox(box);
        itemRepository.save(item);
    }
}
