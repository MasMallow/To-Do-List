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
    public ListEntity findByTime(LocalTime doDate) {
        Optional<ListEntity> result =listRepository.findByDoDate(doDate);
        return result.orElseThrow(()-> new RuntimeException("NO Result: " + doDate));
    }
}
