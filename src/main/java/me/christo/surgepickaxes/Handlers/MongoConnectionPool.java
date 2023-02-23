package me.christo.surgepickaxes.Handlers;


import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.connection.ConnectionPoolSettings.Builder;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ClusterType;

import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MongoConnectionPool {

    private static MongoConnectionPool instance = null;
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;


  //  MongoConnectionPool pool = new MongoConnectionPool("mongodb+srv://christopayne78:chr1st0mongo3042!!@@@pickaxes.4ikq1d7.mongodb.net/?retryWrites=true&w=majority", "Pickaxes", "Pickaxes");

    public MongoConnectionPool() {
        // Set up the MongoDB connection pool
        SocketSettings socketSettings = SocketSettings.builder().connectTimeout(5000, TimeUnit.MILLISECONDS).build();
        ClusterSettings clusterSettings = ClusterSettings.builder()
                .hosts(Arrays.asList(new ServerAddress("mongodb+srv://christopayne78:chr1st0mongo3042!!@@@pickaxes.4ikq1d7.mongodb.net/?retryWrites=true&w=majority")))
                .requiredClusterType(ClusterType.STANDALONE).build();
        ConnectionPoolSettings connectionPoolSettings = ConnectionPoolSettings.builder()
                .maxSize(100).maxWaitQueueSize(10).maxWaitTime(5000, TimeUnit.MILLISECONDS).build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.applySettings(clusterSettings))
                .applyToSocketSettings(builder -> builder.applySettings(socketSettings))
                .applyToConnectionPoolSettings(builder -> builder.applySettings(connectionPoolSettings))
                .build();
        mongoClient = MongoClients.create(settings);

        // Get the database and collection
        database = mongoClient.getDatabase("Pickaxes");
        collection = database.getCollection("Pickaxes");
    }

    public MongoCollection<Document> getConnection() {
        return collection;
    }

    public static MongoConnectionPool getInstance() {
        if (instance == null) {
            instance = new MongoConnectionPool();
        }
        return instance;
    }



    public void close() {
        mongoClient.close();
    }

    public static MongoClient acquire() {
        return MongoConnectionPool.getInstance().acquire();
    }

    public static void release(MongoClient client) {
        client.close();
    }

    public void release(MongoCollection<Document> collection) {
        release(collection);
    }

    public int getValue(Player p, String enchantment) {

        MongoClient client = acquire();

        try {
            MongoDatabase database = mongoClient.getDatabase("Pickaxes");
            MongoCollection<Document> collection = database.getCollection("Pickaxes");
            Document query = new Document("name", p.getName());
            FindIterable<Document> result = collection.find(query);
            Document document = result.first();
            if (document != null) {
                return document.getInteger(enchantment);
                // Do something with the email value...
            }
        } finally {
            release(client);
        }
        return 0;
    }

    public boolean documentExists(Document query) {
        MongoCollection<Document> collection = null;
        try {
            collection = getConnection();
            return collection.find(query).first() != null;
        } finally {
            if (collection != null) {
                release(collection);
            }
        }
    }

    //public class SomeOtherClass {
    //    public void someMethod() {
    //        try (MongoClient client = MongoConnectionPool.acquire()) {
    //            MongoDatabase database = client.getDatabase("Pickaxes");
    //            MongoCollection<Document> collection = database.getCollection("Pickaxes");
    //            Document query = new Document("name", "John Doe");
    //            Document result = collection.find(query).first();
    //            if (result != null) {
    //                // Do something with the result...
    //            }
    //        }
    //    }
    //}
}
