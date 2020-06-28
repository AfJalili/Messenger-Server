package server.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.LoginData;

public class DBStuff {
    public static MongoClient mongoClient;
    protected static MongoDatabase messengerDb;
    protected static MongoCollection accountsColl;
    protected static MongoCollection<LoginData> loginColl;
}
