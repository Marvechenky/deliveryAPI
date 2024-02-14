package com.marvis.deliveryapi.service;

import com.marvis.deliveryapi.data.dtos.request.BoxCreationRequest;
import com.marvis.deliveryapi.data.dtos.response.BoxCreationResponse;
import com.marvis.deliveryapi.data.dtos.response.EmptyBox;
import com.marvis.deliveryapi.data.model.Item;

import java.util.List;

public interface BoxService {

    BoxCreationResponse createBox(BoxCreationRequest boxCreationRequest);

    String loadBoxWithItems(String txref, List<Item> items);

    List<Item> checkLoadedItems(String txref);

    List<EmptyBox> checkAvailableBoxes();

    int checkBatteryLevel(String txref);
}
