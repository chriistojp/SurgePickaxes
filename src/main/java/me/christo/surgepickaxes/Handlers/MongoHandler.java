package me.christo.surgepickaxes.Handlers;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bukkit.entity.Player;

public class MongoHandler {

    public static boolean checkIfDocumentExists(Player p) {


        MongoClient client = MongoConnection.acquire();
        Boolean documentExists = false;

        try {
            MongoDatabase database = client.getDatabase("Pickaxes");
            MongoCollection<Document> collection = database.getCollection("Pickaxes");
            Document query = new Document("name", p.getName());
            FindIterable<Document> result = collection.find(query);
            documentExists = result.iterator().hasNext();
        } finally {
            MongoConnection.release(client);
        }

        return documentExists;


    }

    public static void createNewDocument(Player p) {

      //  MongoCollection<Document> collection = MongoConnectionPool.getInstance().getConnection();

        MongoClient client = MongoConnection.acquire();
        MongoDatabase database = client.getDatabase("Pickaxes");
        MongoCollection<Document> collection = database.getCollection("Pickaxes");


        try {
            Document document = new Document("name", p.getName())
                    .append("level", 0)
                    .append("xp", 0)
                    .append("efficiency", 0)
                    .append("fortune", 0)
                    .append("gemfinder", 0)
                    .append("shatterproof", 0)
                    .append("rampage", 0)
                    .append("greed", 0)
                    .append("jackpot", 0);
            collection.insertOne(document);
        } finally {
            MongoConnection.release(client);
        }


    }

    public static int getValue(Player p, String enchantment) {

        MongoClient client = MongoConnection.acquire();

        try {
            MongoDatabase database = client.getDatabase("Pickaxes");
            MongoCollection<Document> collection = database.getCollection("Pickaxes");
            Document query = new Document("name", p.getName());
            FindIterable<Document> result = collection.find(query);
            Document document = result.first();
            if (document != null) {
                return document.getInteger(enchantment);
                // Do something with the email value...
            }
        } finally {
            MongoConnection.release(client);
        }
        return 0;
    }

    public static void setValue(Player p, String enchantment, int value) {

        MongoClient client = MongoConnection.acquire();

        try {
            MongoDatabase database = client.getDatabase("Pickaxes");
            MongoCollection<Document> collection = database.getCollection("Pickaxes");
            Document query = new Document("name", p.getName());
            FindIterable<Document> result = collection.find(query);
            Document document = result.first();
            if (document != null) {
                document.put(enchantment, value);
                collection.replaceOne(query, document);
            } else {
                document = new Document("name", p.getName());
                document.put(enchantment, value);
                collection.insertOne(document);
            }
        } finally {
            MongoConnection.release(client);
        }
    }
}