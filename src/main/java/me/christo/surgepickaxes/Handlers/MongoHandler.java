package me.christo.surgepickaxes.Handlers;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bukkit.entity.Player;

public class MongoHandler {

    public static boolean checkIfDocumentExists(Player p) {


        MongoClient client = MongoConnectionPool.acquire();
        Boolean documentExists = false;

        try {
            MongoDatabase database = client.getDatabase("Pickaxes");
            MongoCollection<Document> collection = database.getCollection("Pickaxes");
            Document query = new Document("name", p.getName());
            FindIterable<Document> result = collection.find(query);
            documentExists = result.iterator().hasNext();
        } finally {
            MongoConnectionPool.release(client);
        }

        return documentExists;


    }

    public static void createNewDocument(Player p) {

      //  MongoCollection<Document> collection = MongoConnectionPool.getInstance().getConnection();

        MongoClient client = MongoConnectionPool.acquire();
        MongoDatabase database = client.getDatabase("Pickaxes");
        MongoCollection<Document> collection = database.getCollection("Pickaxes");


        try {
            Document document = new Document("name", p.getName())
                    .append("level", 0)
                    .append("efficiency", 0)
                    .append("fortune", 0)
                    .append("gemfinder", 0)
                    .append("shatterproof", 0)
                    .append("rampage", 0)
                    .append("greed", 0)
                    .append("jackpot", 0);
            collection.insertOne(document);
        } finally {
            MongoConnectionPool.release(client);
        }


        // Insert the document into the collection
       // collection.insertOne(document);

        // Close the connection pool


        //Returns the connection to the pull
      //  MongoConnectionPool.getInstance().releaseConnection(collection);

    }

}