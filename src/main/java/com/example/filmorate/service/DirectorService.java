package com.example.filmorate.service;

import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.storage.dao.impl.DirectorDbStorage;
import com.example.filmorate.storage.model.Director;
import com.example.filmorate.storage.model.type.MPA;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DirectorService {
    private final DirectorDbStorage directorDbStorage;

    public Optional<Director> getDirector(int id) {
        return Optional.of(directorDbStorage.getDirector(id)
                .orElseThrow(() -> new NotFoundException("Режиссёр с указанным ID не найден")));
    }

    public Collection<Director> getAllDirectors() {
        return directorDbStorage.getAllDirectors();
    }
}
