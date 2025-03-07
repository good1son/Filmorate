package com.example.filmorate.comparator;

import com.example.filmorate.storage.model.Film;

import java.util.Comparator;

public class LikeComparator implements Comparator<Film> {
    @Override
    public int compare(Film o1, Film o2) {
        return o2.getLikes() - o1.getLikes();
    }
}
