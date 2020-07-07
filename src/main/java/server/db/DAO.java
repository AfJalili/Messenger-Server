package server.db;

import model.*;

import java.io.Serializable;
import java.util.ArrayList;

public interface DAO {

    Boolean createNewAccount(NewAccount newAccount);

    Boolean checkLogin(LoginData loginData);

    Serializable messageHandler(NewMessage newMessage);

     ArrayList<AccNameAndProfilePic> getAllUsersInfo();

    ArrayList<Object> getConversationInfo(String accName);

    UserInfo getUserInfo(String accName);

    NewMessage newMessageListener(String accName);

    AllMessages getAllMessagesOfConversation(RequestConversation rc);

    SearchResult searchAccName(String searchExpr);

    Boolean addAccProfilePic(String aacName, String img);

    Boolean changeAccProfilePic(String aacName, String img);

}
