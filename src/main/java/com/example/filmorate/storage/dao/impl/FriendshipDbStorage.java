package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.FriendshipStorage;
import com.example.filmorate.storage.dto.UserDTO;
import com.example.filmorate.storage.util.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FriendshipDbStorage implements FriendshipStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void sendRequest(Integer senderId, Integer receiverId) {
        String sql = "INSERT INTO friendships (sender_id, receiver_id, status_id, last_action_user_id) " +
                "VALUES (?, ?, 1, ?)";
        jdbcTemplate.update(sql, senderId, receiverId, senderId);
    }

    @Override
    public void acceptRequest(Integer requestId) {
        String sql = "UPDATE friendships SET status_id = 2, last_action_user_id = receiver_id, " +
                "updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(sql, requestId);
    }

    @Override
    public void declineRequest(Integer requestId) {
        String sql = "UPDATE friendships SET status_id = 3, last_action_user_id = receiver_id, " +
                "updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(sql, requestId);
    }

    @Override
    public void deleteRequest(Integer requestId) {
        String sql = "DELETE from friendships WHERE id = ?";
        jdbcTemplate.update(sql, requestId);
    }

    @Override
    public List<UserDTO> getFriends(Integer id) {
        String sql = "SELECT u.login, u.name, u.email FROM friendships f JOIN users u ON " +
                "(f.sender_id = u.id OR f.receiver_id = u.id) AND u.id != ? " +
                "WHERE (f.sender_id = ? OR f.receiver_id = ?) AND f.status_id = 2";
        return jdbcTemplate.query(sql, new UserDTOMapper(), id, id, id);
    }

    @Override
    public Collection<UserDTO> getIncomingFriendRequests(Integer id) {
        String sql = "SELECT u.login, u.name, u.email FROM friendships f JOIN users u ON " +
                "f.sender_id = u.id " +
                "WHERE f.receiver_id = ? AND f.status_id = 1";
        return jdbcTemplate.query(sql, new UserDTOMapper(), id);
    }

    @Override
    public Collection<UserDTO> getOutgoingFriendRequests(Integer id) {
        String sql = "SELECT u.login, u.name, u.email FROM friendships f JOIN users u ON " +
                "f.receiver_id = u.id " +
                "WHERE f.sender_id = ? AND f.status_id = 1";
        return jdbcTemplate.query(sql, new UserDTOMapper(), id);
    }

    @Override
    public Collection<UserDTO> getCommonFriends(Integer id, Integer otherId) {
        String sql = "SELECT u.login, u.name, u.email FROM friendships f1 JOIN friendships f2 ON " +
                "f1.sender_id = f2.sender_id OR f1.receiver_id = f2.receiver_id " +
                "JOIN users u ON (f1.sender_id = u.id OR f1.receiver_id = u.id) " +
                "WHERE (f1.sender_id = ? OR f1.receiver_id = ?) " +
                "AND (f2.sender_id = ? OR f2.receiver_id = ?) " +
                "AND f1.status_id = 2 AND f2.status_id = 2 AND u.id NOT IN (?, ?)";
        return jdbcTemplate.query(sql, new UserDTOMapper(), id, id, otherId, otherId, id, otherId);
    }

    @Override
    public void deleteFriendship(Integer id) {
        String sql = "DELETE FROM friendships WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean isRequestExists(Integer senderId, Integer receiverId) {
        String sql = "SELECT COUNT(id) FROM friendships " +
                "WHERE (sender_id = ? AND receiver_id = ? OR sender_id = ? AND receiver_id = ?) " +
                "AND status_id = 1 LIMIT 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, senderId, receiverId, receiverId, senderId);
        return count != null && count > 0;
    }

    public boolean isRequestExists(Integer requestId) {
        String sql = "SELECT COUNT(id) FROM friendships WHERE id = ? AND status_id = 1 LIMIT 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, requestId);
        return count != null && count > 0;
    }

    public boolean isAlreadyFriends(Integer senderId, Integer receiverId) {
        String sql = "SELECT COUNT(id) FROM friendships " +
                "WHERE (sender_id = ? AND receiver_id = ? OR sender_id = ? AND receiver_id = ?) " +
                "AND status_id = 2 LIMIT 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, senderId, receiverId, receiverId, senderId);
        return count != null && count > 0;
    }

    public boolean isAlreadyFriends(Integer friendshipId) {
        String sql = "SELECT COUNT(id) FROM friendships WHERE id = ? AND status_id = 2 LIMIT 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, friendshipId);
        return count != null && count > 0;
    }

    public boolean isRequestDecline(Integer requestId) {
        String sql = "SELECT COUNT(id) FROM friendships WHERE id = ? AND status_id = 3 LIMIT 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, requestId);
        return count != null && count > 0;
    }
}
