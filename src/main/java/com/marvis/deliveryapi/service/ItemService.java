package com.marvis.deliveryapi.service;
import com.marvis.deliveryapi.data.model.Item;

public interface ItemService {

    void saveItem(Item item, String txref);
}
