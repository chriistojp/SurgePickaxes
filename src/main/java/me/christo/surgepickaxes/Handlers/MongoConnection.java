package me.christo.surgepickaxes.Handlers;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.util.ArrayDeque;
import java.util.Queue;

public class MongoConnection {

    private static final String connectionString = "mongodb+srv://christopayne78:1BHVHJ1oKtIicI7l@pickaxes.6lkdb6b.mongodb.net/?retryWrites=true&w=majority";

    private static final int maxPoolSize = 10;

    private static final Queue<MongoClient> pool = new ArrayDeque<>(10);


    public static synchronized MongoClient acquire() {

        if (pool.isEmpty())
            return createNewClient();
        return pool.remove();
    }

    public static synchronized void release(MongoClient client) {
        if (pool.size() < 10) {
            pool.add(client);
        } else {
            client.close();
        }
    }

    private static MongoClient createNewClient() {
        return MongoClients.create("mongodb+srv://christopayne78:1BHVHJ1oKtIicI7l@pickaxes.6lkdb6b.mongodb.net/?retryWrites=true&w=majority");
    }
}