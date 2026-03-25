package com.jvault;

import com.jvault.core.DataStore;
import com.jvault.server.JVaultServer;

public class Main {
    public static void main(String[] args){
        System.out.println("Starting J_Vault Engine...");
        DataStore store = new DataStore();

        JVaultServer jvs = new JVaultServer(8888, store);
        jvs.start();
    }
}
