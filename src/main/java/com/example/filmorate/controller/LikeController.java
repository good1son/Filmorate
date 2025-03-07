package com.example.filmorate.controller;

import com.example.filmorate.service.LikeService;
import com.example.filmorate.storage.model.Film;
import com.example.filmorate.storage.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LikeController {
    private final LikeService likeService;

    @GetMapping("films/{id}/likes")
    public Collection<User> getFilmLikes(@PathVariable int id) {
        return likeService.getFilmLikes(id);
    }

    @GetMapping("users/{id}/likes")
    public Collection<Film> getUserLikes(@PathVariable int id) {
        return likeService.getUserLikes(id);
    }

    @PutMapping("films/{filmId}/like/{userId}")
    public void putLike(@PathVariable int filmId, @PathVariable int userId) {
        likeService.putLike(filmId, userId);
    }

    @DeleteMapping("films/{filmId}/like/{userId}")
    public void removeLike(@PathVariable int filmId, @PathVariable int userId) {
        likeService.removeLike(filmId, userId);
    }
}
