package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.type.MPA;

import java.util.Collection;
import java.util.Optional;

public interface MpaStorage {
    public Optional<MPA> getMpa(int id);
    public Integer getMpaId(MPA mpa);
    public Collection<MPA> getAllMpa();
}
