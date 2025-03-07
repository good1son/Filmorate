package com.example.filmorate.storage.dao;

import java.util.Collection;

public interface LikeStorage {
    public void putLike(int filmId, int userId);
    public void removeLike(int filmId, int userId);
    public Collection<Integer> getFilmLikes(int id);
    public Collection<Integer> getUserLikes(int id);
}
