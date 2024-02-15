package com.marvis.deliveryapi.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name field is required")
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$",
            message = "Name field should contain only uppercase and lower case letters, digits, hyphen and underscore")
    @Column(name = "item_name")
    private String name;

    @NotNull(message = "Weight field cannot be null" )
    @Positive(message = "Weight cannot be a negative value")
    @Column(name = "item_weight")
    private double weight;

    @Pattern(regexp = "^[A-Z0-9_]+$",
            message = "Code field should contain only uppercase letters, digits, and underscore")
    @NotBlank(message = "Code field is required")
    @Column(name = "item_code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "box_txref")
    private Box box;


    public Item(String name, double weight, String code) {
        this.name = name;
        this.weight = weight;
        this.code = code;

    }
}
