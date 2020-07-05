package server.db;

import model.AccNameAndProfilePic;
import model.LoginData;
import model.NewAccount;
import model.NewMessage;

import java.util.ArrayList;

public interface DbAccessObj {


    Boolean createNewAccount(NewAccount newAccount);

    Boolean checkLogin(LoginData loginData);

    Boolean messageHandler(NewMessage newMessage);

     ArrayList<AccNameAndProfilePic> getAllUsersInfo();

    ArrayList<NewMessage> newMessageNotifier(String accName);

    ArrayList<Object> getConversationInfo(String accName);

}
