package com.example.filmorate.service;

import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.storage.model.User;
import com.example.filmorate.storage.dao.impl.UserDbStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {
    private final UserDbStorage userDbStorage;

    public void addUser(User user) {
        userDbStorage.add(user);
    }

    public Optional<User> findUserById(int id) {
        return Optional.of(userDbStorage.get(id)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден")));
    }

    public Collection<User> getUsers() {
        Collection<User> users = userDbStorage.getUsers();
        if (users.isEmpty())
            throw new NotFoundException("Пользователи не найдены");
        return users;
    }

    public Collection<User> getUsers(Collection<Integer> idList) {
        return idList.stream()
                .map(this::findUserById)
                .flatMap(Optional::stream)
                .toList();
    }

    public void updateUser(int id, User user) {
        User updatedUser = findUserById(id).get();
        if (user.getEmail() != null)
            updatedUser.setEmail(user.getEmail());
        if (user.getLogin() != null)
            updatedUser.setLogin(user.getLogin());
        if (user.getName() != null)
            updatedUser.setName(user.getName());
        if (user.getBirthday() != null
                && user.getBirthday().isAfter(LocalDate.of(1895,12,27))
                && user.getBirthday().isBefore(LocalDate.now().plusDays(1)))
            updatedUser.setBirthday(user.getBirthday());
        userDbStorage.update(updatedUser);
    }

    public void deleteUserById(int id) {
        userDbStorage.delete(id);
    }


}
