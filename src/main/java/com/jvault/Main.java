package com.jvault;

import com.jvault.core.DataStore;

public class Main {
    public static void main(String[] args){
        System.out.println("Starting J_Vault Engine...");
        DataStore store = new DataStore();

        // 1. Test Put
        store.put("user:1001","Manas");
        store.put("config:port",6379);

        // 2. Test Get (Existing)
        System.out.println("User 1001: " + store.get("user:1001").orElse("Key Not Found"));

        // 3. Test Get (Non-Existing)
        System.out.println("User 1002: " + store.get("user:1002").orElse("Key Not Found"));

        // 4. Test Delete
        store.delete("user:1001");
        System.out.println("User 1001 after delete: " + store.get("user:1001").orElse("Key Not Found"));

        // 5. Test Clear
        store.clear();
        System.out.println("Port after clear: " + store.get("config:port").orElse("Key Not Found"));
    }
}
