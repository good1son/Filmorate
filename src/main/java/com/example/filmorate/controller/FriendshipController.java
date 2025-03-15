package com.example.filmorate.controller;

import com.example.filmorate.service.FriendshipService;
import com.example.filmorate.storage.dto.FriendRequestDTO;
import com.example.filmorate.storage.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/friendship")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FriendshipController {
    private final FriendshipService friendshipService;

    @GetMapping("/users/{id}/friends")
    public Collection<UserDTO> getFriends(@PathVariable int id) {
        return friendshipService.getFriends(id);
    }

    @GetMapping("/users/{id}/requests")
    public Collection<UserDTO> getFriendRequests(@PathVariable int id,
                                                 @RequestParam(defaultValue = "in") String status) {
        return friendshipService.getFriendRequests(id, status);
    }

    @GetMapping("/common-friends")
    public Collection<UserDTO> getCommonFriends(@RequestParam Integer userId, @RequestParam Integer otherUserId) {
        return friendshipService.getCommonFriends(userId, otherUserId);
    }

    @PostMapping("/requests")
    public void sendRequest(@RequestBody FriendRequestDTO request) {
        friendshipService.sendRequest(request.getSenderId(), request.getReceiverId());
    }

    @PutMapping("/requests/{requestId}/accept")
    public void acceptRequest(@PathVariable int requestId) {
        friendshipService.acceptRequest(requestId);
    }

    @PutMapping("/requests/{requestId}/decline")
    public void declineRequest(@PathVariable int requestId) {
        friendshipService.declineRequest(requestId);
    }

    @DeleteMapping("/requests/{requestId}")
    public void deleteRequest(@PathVariable int requestId) {
        friendshipService.deleteRequest(requestId);
    }

    @DeleteMapping("/{friendshipId}")
    public void deleteFriendship(@PathVariable int friendshipId ) {
        friendshipService.deleteFriendship(friendshipId);
    }
}
