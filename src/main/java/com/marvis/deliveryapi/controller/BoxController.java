package com.marvis.deliveryapi.controller;

import com.marvis.deliveryapi.data.dtos.request.BoxCreationRequest;
import com.marvis.deliveryapi.data.dtos.request.ItemsRequestDTO;
import com.marvis.deliveryapi.data.dtos.response.BoxCreationResponse;
import com.marvis.deliveryapi.data.model.Item;
import com.marvis.deliveryapi.service.BoxService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BoxController {

    private final BoxService boxService;

    @PostMapping("/box/create")
    public ResponseEntity<BoxCreationResponse> createBox(@RequestBody @Valid BoxCreationRequest boxCreationRequest) {
        BoxCreationResponse createdBox = boxService.createBox(boxCreationRequest);
        return new ResponseEntity<>(createdBox, HttpStatus.CREATED);

    }


    @PostMapping("/box/{txref}/load-items")
    public ResponseEntity<?> loadBox(@PathVariable String txref, @RequestBody ItemsRequestDTO itemsRequestDTOs) {
        var message = "Items loaded into box successfully";
         boxService.loadBoxWithItems(txref, itemsRequestDTOs.getItems());
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/box/{txref}/view-items")
    public ResponseEntity<List<Item>> viewLoadedItems(@PathVariable String txref) {
        List<Item> loadedItems = boxService.checkLoadedItems(txref);
        return new ResponseEntity<>(loadedItems, HttpStatus.OK);
    }

    @GetMapping("/box/available-boxes")
    public ResponseEntity<?> viewAvailableBoxes() {
        var availableBoxes = boxService.checkAvailableBoxes();
        return new ResponseEntity<>(availableBoxes, HttpStatus.OK);
    }


    @GetMapping("/box/{txref}/battery-level")
    public ResponseEntity<?> viewBatteryLevel(@PathVariable String txref) {
        var batterLevel = boxService.checkBatteryLevel(txref);
        return new ResponseEntity<>(batterLevel, HttpStatus.OK);
    }

}
