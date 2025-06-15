package com.To_Do_List.To_Do.List.repository;

import com.To_Do_List.To_Do.List.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface ListRepository extends JpaRepository<ListEntity,Long> {
    Optional<ListEntity> findByDoDate(LocalTime doDate);
}
