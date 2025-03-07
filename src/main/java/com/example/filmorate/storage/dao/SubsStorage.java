package com.example.filmorate.storage.dao;

import java.util.Collection;

public interface SubsStorage {
    public void addSubscribe(int id, int subsId);
    public Collection<Integer> getSubscribers(int id);
    public Collection<Integer> commonSubscribers(int id, int otherId);
    public void deleteSubscribe(int id, int subsId);
}
