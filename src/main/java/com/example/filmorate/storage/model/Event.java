package com.example.filmorate.storage.model;

import com.example.filmorate.storage.model.type.ACTION;
import com.example.filmorate.storage.model.type.TARGET;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {
    private Integer id;
    private Integer userId;
    private ACTION actionType;
    private Integer targetId;
    private TARGET targetType;
    private LocalDateTime createdAt;
}
