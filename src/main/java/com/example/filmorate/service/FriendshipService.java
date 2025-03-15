package com.example.filmorate.service;

import com.example.filmorate.exception.AlreadyExistsException;
import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.storage.dao.impl.FriendshipDbStorage;
import com.example.filmorate.storage.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FriendshipService {
    private final FriendshipDbStorage friendshipDbStorage;

    public void sendRequest(Integer senderId, Integer receiverId) {
        if (friendshipDbStorage.isAlreadyFriends(senderId, receiverId))
            throw new AlreadyExistsException("Пользователи уже являются друзьями");
        if (friendshipDbStorage.isRequestExists(senderId, receiverId))
            throw new AlreadyExistsException("Запрос в друзья уже был отправлен");
        friendshipDbStorage.sendRequest(senderId, receiverId);
    }

    public void acceptRequest(Integer requestId) {
        if (friendshipDbStorage.isAlreadyFriends(requestId))
            throw new AlreadyExistsException("Пользователи уже являются друзьями");
        if (friendshipDbStorage.isRequestExists(requestId))
            friendshipDbStorage.acceptRequest(requestId);
        else throw new NotFoundException("Запрос в друзья не найден");
    }

    public void declineRequest(Integer requestId) {
        if (friendshipDbStorage.isRequestDecline(requestId))
            throw new AlreadyExistsException("Заявка уже была отклонена");
        if (friendshipDbStorage.isRequestExists(requestId))
            friendshipDbStorage.declineRequest(requestId);
        else throw new NotFoundException("Пользователи не являются друзьями");
    }

    public void deleteRequest(Integer requestId) {
        if (friendshipDbStorage.isAlreadyFriends(requestId))
            throw new AlreadyExistsException("Удаление невозможно, т.к. пользователи уже являются друзьями");
        if (!friendshipDbStorage.isRequestExists(requestId))
            throw new NotFoundException("Запрос в друзья не найден");
        friendshipDbStorage.deleteRequest(requestId);
    }

    public Collection<UserDTO> getFriends(Integer id) {
        return friendshipDbStorage.getFriends(id);
    }

    public Collection<UserDTO> getFriendRequests(Integer id, String status) {
        if (status.equalsIgnoreCase("out"))
            return friendshipDbStorage.getOutgoingFriendRequests(id);
        else if (status.equalsIgnoreCase("in"))
            return friendshipDbStorage.getIncomingFriendRequests(id);
        throw new IllegalArgumentException("Неверный запрос");
    }

    public Collection<UserDTO> getCommonFriends(Integer id, Integer otherId) {
        return friendshipDbStorage.getCommonFriends(id, otherId);
    }

    public void deleteFriendship(Integer id) {
        if (!friendshipDbStorage.isAlreadyFriends(id))
            throw new NotFoundException("Пользователи не являются друзьями");
        friendshipDbStorage.deleteFriendship(id);
    }
}
