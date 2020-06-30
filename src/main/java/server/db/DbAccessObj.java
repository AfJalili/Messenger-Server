package server.db;

import model.AccNameAndProfilePic;
import model.LoginData;
import model.NewAccount;
import model.NewMessage;

import java.util.ArrayList;

public interface DbAccessObj {


    Boolean createNewAccount(NewAccount newAccount);

    Boolean checkLogin(LoginData loginData);

     Boolean MessageHandler(NewMessage newMessage);

     ArrayList<AccNameAndProfilePic> getAllUsersInfo();

}
