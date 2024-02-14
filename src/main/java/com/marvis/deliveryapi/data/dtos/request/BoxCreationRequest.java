package com.marvis.deliveryapi.data.dtos.request;

import com.marvis.deliveryapi.data.model.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoxCreationRequest {

    @NotBlank
    @Size(max = 20, message = "txrex cannot exceed 20 characters")
    private String txref;

    @NotNull(message = "Weight limit cannot be null")
    @Positive(message = "Weight limit cannot be negative value")
    @DecimalMax(value = "500.0", message = "Weight limit cannot exceed 500grams")
    private double weightLimit;

    @NotNull(message = "Battery capacity cannot be null")
    @Positive(message = "Battery capacity cannot be negative value")
    private int batteryCapacity;

    @Enumerated(value = EnumType.STRING)
    private State boxState;
}
