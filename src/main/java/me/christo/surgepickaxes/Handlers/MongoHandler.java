//package me.christo.surgepickaxes.Handlers;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.MongoCollection;
//import org.bson.Document;
//
//public class MongoHandler {
//
//    private MongoClient mongoClient;
//    private MongoDatabase database;
//
//    public void connect(String uri, String databaseName) {
//        try {
//            MongoClientURI mongoUri = new MongoClientURI(uri);
//            mongoClient = new MongoClient(mongoUri);
//            database = mongoClient.getDatabase(databaseName);
//            System.out.println("Connected to MongoDB database: " + databaseName);
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//        }
//    }
//
//    // additional methods for interacting with the database, such as creating collections, inserting documents, etc.
//}
