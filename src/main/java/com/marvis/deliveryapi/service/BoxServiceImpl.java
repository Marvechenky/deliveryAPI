package com.marvis.deliveryapi.service;

import com.marvis.deliveryapi.data.dtos.request.BoxCreationRequest;
import com.marvis.deliveryapi.data.dtos.response.BoxCreationResponse;
import com.marvis.deliveryapi.data.dtos.response.EmptyBox;
import com.marvis.deliveryapi.data.model.Box;
import com.marvis.deliveryapi.data.model.Item;
import com.marvis.deliveryapi.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService{

    private final BoxRepository boxRepository;

    @Override
    public BoxCreationResponse createBox(BoxCreationRequest boxCreationRequest) {
        Box box = Box.builder()
                .txref(boxCreationRequest.getTxref())
                .weightLimit(boxCreationRequest.getWeightLimit())
                .batteryCapacity(boxCreationRequest.getBatteryCapacity())
                .boxState(boxCreationRequest.getBoxState())
                .build();
        Box createdBox = boxRepository.save(box);

        return BoxCreationResponse.builder()
                .txref(createdBox.getTxref())
                .weightLimit(createdBox.getWeightLimit())
                .batteryCapacity(createdBox.getBatteryCapacity())
                .boxState(createdBox.getBoxState())
                .build();
    }


    @Override
    public String loadBoxWithItems(String txref, List<Item> items) {
        return null;
    }

    @Override
    public List<Item> checkLoadedItems(String txref) {
        return null;
    }

    @Override
    public List<EmptyBox> checkAvailableBoxes() {
        return null;
    }

    @Override
    public int checkBatteryLevel(String txref) {
        return 0;
    }
}
