package com.marvis.deliveryapi.service;

import com.marvis.deliveryapi.data.dtos.request.BoxCreationRequest;
import com.marvis.deliveryapi.data.dtos.response.BoxCreationResponse;
import com.marvis.deliveryapi.data.model.Box;
import com.marvis.deliveryapi.data.model.Item;
import com.marvis.deliveryapi.data.model.State;
import com.marvis.deliveryapi.exception.BoxNotFoundException;
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
        Box box = boxRepository.findByTxrefIgnoreCase(txref);
        if (box == null) {
            return "Box with txref " + txref + " not found";
        }
        if (!box.getBoxState().equals("IDLE")) {
            return "Box is not in IDLE state and cannot be loaded";
        }
        double totalWeight = items.stream().mapToDouble(Item::getWeight).sum();
        if (totalWeight > box.getWeightLimit()) {
            return "Total weight of items exceeds the weight limit of the box.";
        }
        box.setItems(items);
        box.setBoxState(State.LOADED);
        boxRepository.save(box);

        return "Items loaded into the box successfully";

    }

    @Override
    public List<Item> checkLoadedItems(String txref) {
        Box box = boxRepository.findByTxrefIgnoreCase(txref);
        if (box == null) {
            throw new BoxNotFoundException("Box with txref " + txref + " not found");
        }
        return box.getItems();
    }

    @Override
    public List<Box> checkAvailableBoxes() {
        return boxRepository.findByBoxState(State.valueOf("IDLE"));
    }

    @Override
    public int checkBatteryLevel(String txref) {
        Box box = boxRepository.findByTxrefIgnoreCase(txref);
        if (box == null) {
            throw new BoxNotFoundException("Box with txref " + txref + " not found");
        }
        return box.getBatteryCapacity();
    }
}
