package server.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.Account;
import models.LoginData;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DBStuff {
    public static MongoClient mongoClient = MongoClients.create();

    // Creating a codec registry for POJOs
    public static CodecRegistry  pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    public static MongoDatabase messengerDb = mongoClient.getDatabase("messenger_db").withCodecRegistry(pojoCodecRegistry);

    public static MongoCollection<Document> accountsColl = messengerDb.getCollection("accounts");
    public static MongoCollection<Account> accountsCollByObj = messengerDb.getCollection("accounts", Account.class);

    public static MongoCollection<Document> loginColl = messengerDb.getCollection("login");
    public static MongoCollection<LoginData> loginCollByObj = messengerDb.getCollection("login", LoginData.class);

}
