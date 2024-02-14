package com.marvis.deliveryapi.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "txref field is required")
    private String txref;

    @NotNull(message = "Weight limit cannot be null")
    @Positive(message = "Weight Limit cannot be a negative value")
    private double weightLimit;

    @NotNull(message = "Battery capacity field cannot be null")
    @Positive(message = "Battery capacity cannot be a negative value")
    private int batteryCapacity;

    @Enumerated(value = EnumType.STRING)
    private State boxState;

    @OneToMany
    private List<Item> items = new ArrayList<>();
}
