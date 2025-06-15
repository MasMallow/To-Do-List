package com.To_Do_List.To_Do.List.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "todo_list") // แก้ให้ตรงกับ DDL
public class ListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_seq")
    @SequenceGenerator(name = "todo_seq", sequenceName = "todo_sequence",
            allocationSize = 1)
    private Long id;

    @Column(name = "task_name") // เปลี่ยนจาก name เพื่อหลีกเลี่ยง reserved word
    private String name;

    @Column(name = "is_eat")
    private boolean eat;

    @Column(name = "is_exercise")
    private boolean exercise;

    @Column(name = "is_sleep")
    private boolean sleep;

    @Column(name = "do_time") // เปลี่ยนจาก doDate เพื่อให้สื่อความหมายชัดเจน
    @Temporal(TemporalType.TIME) // ระบุ temporal type สำหรับ Oracle
    private LocalTime doDate;

    @Column(name = "created_date") // เปลี่ยนจาก date เพื่อหลีกเลี่ยง reserved word
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;
}