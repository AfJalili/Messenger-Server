package server.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import models.Account;
import models.LoginData;
import models.NewAccount;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DAO implements DbAccessObj {


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
        LoginData ld =  DBStuff.loginCollByObj.find(eq("accountName", accountName)).first();
        return ld == null;

    }

    protected void addToLogins(LoginData loginData) {
        DBStuff.loginCollByObj.insertOne(loginData);
    }

    protected Account fillAccountObj(NewAccount newAccount) {
        return new Account(newAccount.getGender(), newAccount.getAccountName(), newAccount.getUserName(),
                newAccount.getPassword(), newAccount.getProfilePic());
    }

    protected void saveAccount(Account account) {
        MongoCollection<Account> accCollObj = DBStuff.messengerDb.getCollection("accounts", Account.class);
        DBStuff.accountsCollByObj.insertOne(account);
    }

    @Override
    public Boolean checkLogin(LoginData logInForm) {
        return null;
    }
}
