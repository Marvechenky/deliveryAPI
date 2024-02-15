package com.marvis.deliveryapi.data.dtos.request;

import com.marvis.deliveryapi.data.model.Box;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ItemRequest {

    @NotBlank(message = "Name field is required")
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$",
            message = "Name field should contain only uppercase and lower case letters, digits, hyphen and underscore")
    private String name;

    @NotNull(message = "Weight field cannot be null" )
    @Positive(message = "Weight cannot be a negative value")
    private double weight;

    @Pattern(regexp = "^[A-Z0-9_]+$",
            message = "Code field should contain only uppercase letters, digits, and underscore")
    @NotBlank(message = "Code field is required")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "box_txref")
    private Box box;


}
