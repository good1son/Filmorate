package com.example.filmorate.service;

import com.example.filmorate.storage.dao.impl.SubsDbStorage;
import com.example.filmorate.storage.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubsService {
    private final SubsDbStorage subsDbStorage;
    private final UserService userService;

    public Collection<User> getSubscribers(int id) {
        Collection<Integer> idList = subsDbStorage.getSubscribers(id);
        return userService.getUsers(idList);
    }

    public Collection<User> commonSubscribers(int id, int otherId) {
        Collection<Integer> idList = subsDbStorage.commonSubscribers(id, otherId);
        return userService.getUsers(idList);
    }

    public void addSubscribe(int id, int subsId) {
        //добавить проверку пользователей
        subsDbStorage.addSubscribe(id, subsId);
    }

    public void deleteSubscribe(int id, int subsId) {
        //добавить проверку польщователей
        subsDbStorage.deleteSubscribe(id, subsId);
    }
}
