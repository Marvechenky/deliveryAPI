package com.marvis.deliveryapi.controller;

import com.marvis.deliveryapi.data.dtos.request.BoxCreationRequest;
import com.marvis.deliveryapi.data.dtos.response.BoxCreationResponse;
import com.marvis.deliveryapi.service.BoxService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
