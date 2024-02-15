package com.marvis.deliveryapi.service;

import com.marvis.deliveryapi.data.dtos.request.BoxCreationRequest;
import com.marvis.deliveryapi.data.dtos.response.BoxCreationResponse;
import com.marvis.deliveryapi.data.model.Box;
import com.marvis.deliveryapi.data.model.Item;
import com.marvis.deliveryapi.data.model.State;
import com.marvis.deliveryapi.exception.BoxBatteryLowException;
import com.marvis.deliveryapi.exception.BoxNotFoundException;
import com.marvis.deliveryapi.exception.BoxStateException;
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
        Box box = findBox(txref);
        if (!box.getBoxState().equals(State.IDLE)) {
            throw new BoxStateException("Box is not in IDLE state,therefore it cannot be loaded");
        }
        int batteryLevel = checkBatteryLevel(txref);
        if (batteryLevel < 25) {
            throw new BoxBatteryLowException("Battery is critically low, therefore it cannot transit to loading state");
        }
        double totalWeight = items.stream().mapToDouble(Item::getWeight).sum();
        if (totalWeight > box.getWeightLimit()) {
            throw new BoxStateException("Total weight of items exceed box weight limit");
        }
        box.setItems(items);
        box.setBoxState(State.LOADED);
        boxRepository.save(box);

        return "Items loaded into the box successfully";

    }

    @Override
    public List<Item> checkLoadedItems(String txref) {
        Box box = findBox(txref);
        return box.getItems();
    }

    @Override
    public List<Box> checkAvailableBoxes() {
        return boxRepository.findByBoxState(State.valueOf("IDLE"));
    }

    @Override
    public int checkBatteryLevel(String txref) {
       Box box = findBox(txref);
        return box.getBatteryCapacity();
    }

    private Box findBox(String txref) {
        return boxRepository.findByTxrefIgnoreCase(txref)
                .orElseThrow(()
                        ->  new BoxNotFoundException(String.format("Box with %s not found", txref)));
    }

}
