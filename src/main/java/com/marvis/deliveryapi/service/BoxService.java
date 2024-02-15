package com.marvis.deliveryapi.service;

import com.marvis.deliveryapi.data.dtos.request.BoxCreationRequest;
import com.marvis.deliveryapi.data.dtos.request.ItemRequest;
import com.marvis.deliveryapi.data.dtos.response.BoxCreationResponse;
import com.marvis.deliveryapi.data.model.Box;
import com.marvis.deliveryapi.data.model.Item;

import java.util.List;

public interface BoxService {

    BoxCreationResponse createBox(BoxCreationRequest boxCreationRequest);

    void loadBoxWithItems(String txref, List<ItemRequest> itemRequests);

    List<Item> checkLoadedItems(String txref);

    List<Box> checkAvailableBoxes();

    int checkBatteryLevel(String txref);
}
