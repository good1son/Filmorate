package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.Director;

import java.util.Collection;
import java.util.Optional;

public interface DirectorStorage {
    public Optional<Director> getDirector(Integer id);
    public Collection<Director> getAllDirectors();
}
