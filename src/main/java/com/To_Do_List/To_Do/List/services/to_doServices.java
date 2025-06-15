package com.To_Do_List.To_Do.List.services;

import com.To_Do_List.To_Do.List.entity.ListEntity;

import java.sql.Time;
import java.util.List;

public interface to_doServices {
    ListEntity save(ListEntity listEntity);
    List<ListEntity> findAll();
    ListEntity findByTime(Time doDate);
}
