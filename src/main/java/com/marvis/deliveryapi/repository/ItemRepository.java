package com.marvis.deliveryapi.repository;

import com.marvis.deliveryapi.data.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
