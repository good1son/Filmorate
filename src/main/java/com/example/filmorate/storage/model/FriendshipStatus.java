package com.example.filmorate.storage.model;

import com.example.filmorate.storage.model.type.FRIENDSHIP_STATUS;
import lombok.Data;

@Data
public class FriendshipStatus {
    private Integer id;
    private FRIENDSHIP_STATUS status;
}
