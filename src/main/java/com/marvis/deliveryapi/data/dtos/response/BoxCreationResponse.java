package com.marvis.deliveryapi.data.dtos.response;

import com.marvis.deliveryapi.data.model.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BoxCreationResponse {

    private String txref;
    private double weightLimit;
    private int batteryCapacity;
    private State boxState;
}
