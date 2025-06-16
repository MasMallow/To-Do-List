package com.To_Do_List.To_Do.List.repository;

import com.To_Do_List.To_Do.List.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.*;

public interface ListRepository extends JpaRepository<ListEntity,Long> {
    List<ListEntity> findByDoDateBetween(LocalTime startTime, LocalTime endTime);
    List<ListEntity> findByDoDateAfter(LocalTime time);
    List<ListEntity> findByDoDateBefore(LocalTime time);
}
