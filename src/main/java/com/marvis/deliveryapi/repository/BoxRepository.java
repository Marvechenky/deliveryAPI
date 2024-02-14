package com.marvis.deliveryapi.repository;

import com.marvis.deliveryapi.data.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxRepository extends JpaRepository<Box, Integer> {
}
