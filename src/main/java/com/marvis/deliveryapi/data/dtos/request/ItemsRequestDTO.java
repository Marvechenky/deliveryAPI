package com.marvis.deliveryapi.data.dtos.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemsRequestDTO {

    private List<ItemRequest> items;
}
