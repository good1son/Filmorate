package com.example.filmorate.service;

import com.example.filmorate.storage.dao.impl.SearchImpl;
import com.example.filmorate.storage.model.Film;
import com.example.filmorate.storage.model.type.GENRE;
import com.example.filmorate.storage.model.type.MPA;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SearchService {
    private final SearchImpl searchImpl;
    private final FilmService filmService;

    public Collection<Film> searchFilms(String name, GENRE genre, String year, MPA mpa) {
        Collection<Integer> filmIdList = searchImpl.searchFilms(name, genre, year, mpa);
        return filmService.getFilms(filmIdList);
    }
}
