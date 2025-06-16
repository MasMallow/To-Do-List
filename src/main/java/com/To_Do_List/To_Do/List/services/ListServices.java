package com.To_Do_List.To_Do.List.services;

import com.To_Do_List.To_Do.List.entity.ListEntity;

import java.time.LocalTime;
import java.util.List;

public interface ListServices {
    ListEntity save(ListEntity listEntity);
    List<ListEntity> findAll();
    List<ListEntity> findByTimeRange(LocalTime startTime, LocalTime endTime);
    List<ListEntity> findByTimeAfter(LocalTime time);
    List<ListEntity> findByTimeBefore(LocalTime time);
}
