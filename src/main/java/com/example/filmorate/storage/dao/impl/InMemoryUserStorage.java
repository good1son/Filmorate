package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.UserStorage;

import com.example.filmorate.storage.model.User;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryUserStorage implements UserStorage {
    private final AtomicLong id = new AtomicLong(1);
    public final Map<Long, User> users = new HashMap<>();

    @Override
    public void setDataSource(DataSource dataSource) {

    }

    @Override
    public void add(User user) {
    }

    @Override
    public User get(int id) {
        return new User();
    }

    @Override
    public Collection<User> getUsers() {
        return List.of();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }


}
