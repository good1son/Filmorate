package com.example.filmorate.service;

import com.example.filmorate.storage.dao.impl.LikeDbStorage;
import com.example.filmorate.storage.model.Film;
import com.example.filmorate.storage.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LikeService {
    private final LikeDbStorage likeDbStorage;
    private final UserService userService;
    private final FilmService filmService;

    public Collection<User> getFilmLikes(int id) {
        Collection<Integer> idList = likeDbStorage.getFilmLikes(id);
        return userService.getUsers(idList);
    }

    public Collection<Film> getUserLikes(int id) {
        Collection<Integer> idList = likeDbStorage.getUserLikes(id);
        return filmService.getFilms(idList);
    }

    public void putLike(int filmId, int userId) {
        filmService.findFilmById(filmId);  // получение фильма
        User user = userService.findUserById(userId).get();    // и пользователя по ID В качестве проверки id & userId
        if (!getFilmLikes(filmId).contains(user)) {
            likeDbStorage.putLike(filmId, userId);
            filmService.putLike(filmId);
        }
    }

    public void removeLike(int filmId, int userId) {
        filmService.findFilmById(filmId);
        User user = userService.findUserById(userId).get();
        if (getFilmLikes(filmId).contains(user)) {
            likeDbStorage.removeLike(filmId, userId);
            filmService.removeLike(filmId);
        }
    }
}
