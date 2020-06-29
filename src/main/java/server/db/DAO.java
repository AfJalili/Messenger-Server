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
        //  create an object of Account.class and fill it with new Account info
        Account account = fillAccountObj(newAccount);
        //  add info to AccountsColl
        saveAccount(account);
        return true;
    }

    protected boolean isAccountNameAvailable(String accountName) {
        LoginData ld =  DBStuff.accCollByLoginData.find(eq("accountName", accountName)).first();
        return ld == null;

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
    public Boolean checkLogin(LoginData loginData) {
        //  check logins in accounts
        if (!searchAccountsCollForLogin(loginData)) { return false; }
        // TODO if true: change status to online
        changeUserStatusTo(loginData.getAccountName(), "ONLINE");
        return null;
    }

    protected boolean searchAccountsCollForLogin(LoginData loginData) {
        LoginData ld = DBStuff.accCollByLoginData.find(eq("accountName", loginData.getAccountName())).first();
        return (ld != null) && (ld.getPassword().contentEquals(loginData.getPassword()));
    }

    protected void changeUserStatusTo(String accountName, String status) { // ONLINE or OFFLINE
        // TODO for #Matin
        // TODO go to accounts collection, find account using accountName and change status field

    }
}
