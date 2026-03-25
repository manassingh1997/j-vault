package com.jvault.core;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The primary storage engine for J-Vault.
 * Uses ConcurrentHashMap for lock-striping thread safety.
 */

public class DataStore {
    // We use String keys and Object values for maximum flexibility
    private final ConcurrentHashMap<String, Object> storage;

    public DataStore(){
        this.storage = new ConcurrentHashMap<>();
    }

    public void put(String key, Object value){
        if (key == null || value == null){
            throw new IllegalArgumentException("Key or Value cannot be null");
        }
        storage.put(key, value);
    }

    public Optional<Object> get(String key) {
        return Optional.ofNullable(storage.get(key));
    }

    public boolean delete(String key) {
        return storage.remove(key) != null;
    }

    public void clear() {
        storage.clear();
    }
}
