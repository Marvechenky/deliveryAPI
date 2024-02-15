package com.marvis.deliveryapi.repository;
import com.marvis.deliveryapi.data.model.Box;
import com.marvis.deliveryapi.data.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Integer> {

    Optional<Box> findByTxrefIgnoreCase(String txref);

    List<Box> findByBoxState(State boxState);
}
