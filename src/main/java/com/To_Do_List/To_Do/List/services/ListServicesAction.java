package com.To_Do_List.To_Do.List.services;

import com.To_Do_List.To_Do.List.entity.ListEntity;
import com.To_Do_List.To_Do.List.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ListServicesAction implements ListServices {

    private final ListRepository listRepository;

    @Autowired
    public ListServicesAction(ListRepository listRepository){
        this.listRepository = listRepository;
    }

    @Override
    public ListEntity save(ListEntity listEntity){
        return listRepository.save(listEntity);
    }

    @Override
    public List<ListEntity> findAll(){
        return listRepository.findAll();
    }

    @Override
    public List<ListEntity> findByTimeRange(LocalTime startTime, LocalTime endTime) {
        return listRepository.findByDoDateBetween(startTime,endTime);
    }

    @Override
    public List<ListEntity> findByTimeAfter(LocalTime time) {
        return listRepository.findByDoDateAfter(time);
    }

    @Override
    public List<ListEntity> findByTimeBefore(LocalTime time) {
        return listRepository.findByDoDateBefore(time);
    }
}
