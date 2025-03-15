package com.example.filmorate.storage.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Friendship {
    private Integer id;
    private Integer userId;
    private Integer otherUserId;
    private Integer statusId;
    private Integer lastActionUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
