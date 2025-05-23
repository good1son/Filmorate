package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.User;

import javax.sql.DataSource;
import java.util.Collection;

public interface UserStorage {
    public void setDataSource(DataSource dataSource);
    public void add(User user);

    public User get(int id);
    public Collection<User> getUsers();

    public void update(User user);
    public void delete(int id);
}
