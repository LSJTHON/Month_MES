package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    List<Shipping> findAllByOrderNumber_StatusIn(List<Status> statuses);

    List<Shipping> findByShippingDateBefore(LocalDate date);
}
