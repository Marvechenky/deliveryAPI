package com.marvis.deliveryapi.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
    private Long id;

    @NotBlank(message = "txref field is required")
    @Column(name = "box_txref")
    private String txref;

    @NotNull(message = "Weight limit cannot be null")
    @Positive(message = "Weight Limit cannot be a negative value")
    @Column(name = "box_weight_limit")
    private double weightLimit;

    @NotNull(message = "Battery capacity field cannot be null")
    @Positive(message = "Battery capacity cannot be a negative value")
    @Max(value = 100, message = "Battery capacity cannot exceed 100 percent")
    @Column(name = "box_battery_capacity")
    private int batteryCapacity;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "box_state")
    private State boxState;

    @OneToMany(mappedBy = "box", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();
}
