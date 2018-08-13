package Client;

import Common.Models.UserModel;

public class ClientHandler {
    public static void main(String[] args){
        SocketHandler hostConnection = new SocketHandler("localhost", 8080, new UserModel("Example"));
        hostConnection.sendMessage("Test");
    }

}
