package com.marvis.deliveryapi.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

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

    @NotBlank
    private String txref;

    @NotNull
    @Positive
    private double weightLimit;

    @NotNull
    @Positive
    private int batteryCapacity;

    @Enumerated(value = EnumType.STRING)
    private State boxState;

}
