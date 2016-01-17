package com.tpjad.project.utils;

/**
 * Created by Vlad Trenea on 1/17/2016.
 */
public interface AuthenticationCache<K, T> {
    void put(K key, T value);
    T get(K key);
    void remove(K key);
    int size();
    void cleanup();
}
