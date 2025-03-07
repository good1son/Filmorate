package com.example.filmorate.service;

import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.storage.dao.impl.GenreDbStorage;
import com.example.filmorate.storage.model.type.GENRE;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GenreService {
    private final GenreDbStorage genreDbStorage;

    public Optional<GENRE> getGenre(int id) {
        return Optional.of(genreDbStorage.getGenre(id)
                .orElseThrow(() -> new NotFoundException("Жанр с указанным ID не найден")));
    }

    public Map<Integer, GENRE> getGenres() {
        List<GENRE> genreList = new ArrayList<>(genreDbStorage.getGenres());
        return IntStream.range(0, genreList.size())
                .boxed()
                .collect(Collectors.toMap(
                        x -> x,
                        genreList::get)
                );
    }

    public Collection<GENRE> getFilmGenres(int id) {
        return genreDbStorage.getFilmGenres(id);
    }

    public void addGenre(int id, Set<GENRE> genre) {
        List<Integer> genreIdList = genre.stream().map(Enum::ordinal).toList();
        genreDbStorage.addGenre(id, genreIdList);
    }

}
