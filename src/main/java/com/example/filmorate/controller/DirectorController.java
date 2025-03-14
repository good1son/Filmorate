package com.example.filmorate.controller;

import com.example.filmorate.service.DirectorService;
import com.example.filmorate.storage.model.Director;
import com.example.filmorate.storage.model.type.MPA;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DirectorController {
    private final DirectorService directorService;

    @GetMapping
    public Collection<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    public Optional<Director> getDirector(@PathVariable int id) {
        return directorService.getDirector(id);
    }

}
