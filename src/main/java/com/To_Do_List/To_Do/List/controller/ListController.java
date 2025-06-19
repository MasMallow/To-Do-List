package com.To_Do_List.To_Do.List.controller;

import com.To_Do_List.To_Do.List.entity.ListEntity;
import com.To_Do_List.To_Do.List.services.ListServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("/list")
public class ListController {
    private final ListServices listServices;

    @Autowired
    public ListController(ListServices listServices){
        this.listServices = listServices;
    }

    @PostMapping("/ToDo")
    public ResponseEntity<ListEntity> addToDo(@Valid @RequestBody ListEntity list){
        try{
            if (list.getDate()==null){
                list.setDate(new Timestamp(System.currentTimeMillis()));
            }
            if (list.getDoDate()==null){
                list.setDoDate(LocalTime.now());
            }
            if (list.getName() == null || list.getName().trim().isEmpty()){
                return ResponseEntity.badRequest().build();
            }
            ListEntity saveList = listServices.save(list);
            return ResponseEntity.ok(saveList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListEntity>> getAllToDo(){
        try {
            List<ListEntity> lists = listServices.findAll();
            return ResponseEntity.ok(lists);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/time-range")
    public ResponseEntity<List<ListEntity>> findByTimeRange(
            @RequestParam String startTime,
            @RequestParam String endTime){
        try{
            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);
            List<ListEntity> result = listServices.findByTimeRange(start,end);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/time-after")
    public ResponseEntity<List<ListEntity>> findByTimeAfter(@RequestParam String time){
        try {
            LocalTime localTime = LocalTime.parse(time);
            List<ListEntity> result = listServices.findByTimeAfter(localTime);
            return ResponseEntity.ok(result);
        } catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/time-before")
    public ResponseEntity<List<ListEntity>> findByTimeBefore(@RequestParam String time){
        try{
            LocalTime localTime = LocalTime.parse(time);
            List<ListEntity> result = listServices.findByTimeBefore(localTime);
            return  ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/ToDo/{id}")
    public ResponseEntity<ListEntity> updateToDo(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        try {
            Optional<ListEntity> existingEntityOpt = listServices.findById(id);
            if (existingEntityOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            ListEntity existingEntity = existingEntityOpt.get();

            // อัปเดตเฉพาะฟิลด์ที่ส่งมา
            if (updates.containsKey("name")) {
                String name = (String) updates.get("name");
                if (name != null && !name.trim().isEmpty()) {
                    existingEntity.setName(name);
                }
            }
            if (updates.containsKey("sleep")) {
                existingEntity.setSleep((Boolean) updates.get("sleep"));
            }
            if (updates.containsKey("eat")) {
                existingEntity.setEat((Boolean) updates.get("eat"));
            }
            if (updates.containsKey("exercise")) {
                existingEntity.setExercise((Boolean) updates.get("exercise"));
            }
            if (updates.containsKey("doDate")) {
                String doDateStr = (String) updates.get("doDate");
                if (doDateStr != null) {
                    existingEntity.setDoDate(LocalTime.parse(doDateStr));
                }
            }

            ListEntity result = listServices.save(existingEntity);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable Long id){
        try{
            listServices.deleteById(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
