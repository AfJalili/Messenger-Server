package server.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.Account;
import models.LoginData;
import models.NewAccount;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DAO implements DbAccessObj {

    protected MongoClient mongoClient;
    protected MongoDatabase messengerDb;
    protected MongoCollection accountsColl;
    protected MongoCollection<LoginData> loginColl;
    @Override
    public void connectToDatabase() {
        mongoClient = MongoClients.create();
            // Creating a codec registry for POJOs
            CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            // get handle to databases
        messengerDb = mongoClient.getDatabase("messenger_db").withCodecRegistry(pojoCodecRegistry);
        // get handle to collections
        getCollections();
    }

    protected void getCollections() {
        accountsColl = messengerDb.getCollection("accounts");
        loginColl = messengerDb.getCollection("login", LoginData.class);
//         todo more collections
    }

    @Override
    public Boolean createNewAccount(NewAccount newAccount) {
        //  check availability of accountName
        if (!isAccountNameAvailable(newAccount.getAccountName())) { return false; }
        //  if true: add info to loginColl
        addToLogins(new LoginData(newAccount.getAccountName(), newAccount.getPassword()));
        //  create an object of Account.class and fill it with new Account info
        Account account = fillAccountObj(newAccount);
        //  add info to AccountsColl
        saveAccount(account);
        return true;
    }

    protected boolean isAccountNameAvailable(String accountName) {
        // TODO this implementation sucks must change
        LoginData ld =  loginColl.find(eq("accountName", accountName)).first();
        return ld == null;

    }

    protected void addToLogins(LoginData loginData) {
        loginColl.insertOne(loginData);
    }

    protected Account fillAccountObj(NewAccount newAccount) {
        return new Account(newAccount.getGender(), newAccount.getAccountName(), newAccount.getUserName(),
                newAccount.getPassword(), newAccount.getProfilePic());
    }

    protected void saveAccount(Account account) {
        MongoCollection<Account> accColl = messengerDb.getCollection("accounts", Account.class);
        accColl.insertOne(account);
    }

    @Override
    public Boolean checkLogin(LoginData logInForm) {
        return null;
    }
}
