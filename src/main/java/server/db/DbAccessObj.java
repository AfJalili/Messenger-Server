package server.db;

import model.*;

import java.io.Serializable;
import java.util.ArrayList;

public interface DbAccessObj {


    Boolean createNewAccount(NewAccount newAccount);

    Boolean checkLogin(LoginData loginData);

    Boolean messageHandler(NewMessage newMessage);

     ArrayList<AccNameAndProfilePic> getAllUsersInfo();

    ArrayList<NewMessage> newMessageNotifier(String accName);

    ArrayList<Object> getConversationInfo(String accName);

    UserInfo getUserInfo(String accName);


    NewMessage newMessageListener(String accName);

    AllMessages getAllMessagesOfConversation(RequestConversation rc);

    MemberInfo searchAccName(String accName);

}
