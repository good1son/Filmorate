package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.dto.UserDTO;

import java.util.Collection;
import java.util.List;

public interface FriendshipStorage {
    public void sendRequest(Integer senderId, Integer receiverId);
    public void acceptRequest(Integer requestId);
    public void declineRequest(Integer requestId);
    public void deleteRequest(Integer requestId);

    public Collection<UserDTO> getFriends(Integer id);
    public Collection<UserDTO> getIncomingFriendRequests(Integer id);
    public Collection<UserDTO> getOutgoingFriendRequests(Integer id);
    public Collection<UserDTO> getCommonFriends(Integer id, Integer otherId);

    public List<Integer> getFriendsIdList(Integer id);

    public void deleteFriendship(Integer id);
}
