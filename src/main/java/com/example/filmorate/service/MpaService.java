package com.example.filmorate.service;

import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.storage.dao.impl.MpaDbStorage;
import com.example.filmorate.storage.model.type.MPA;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MpaService {
    private final MpaDbStorage mpaDbStorage;

    public Optional<MPA> getMpa(int id) {
        return Optional.of(mpaDbStorage.getMpa(id)
                .orElseThrow(() -> new NotFoundException("Рейтинг MPA с указанным ID не найден")));
    }

    public Map<Integer, MPA> getAllMpa() {
        List<MPA> mpaList = new ArrayList<>(mpaDbStorage.getAllMpa());
        return IntStream.range(0, mpaList.size())
                .boxed()
                .collect(Collectors.toMap(
                        x -> x,
                        mpaList::get));
    }
}
