package Server;

import Common.Models.ClientModel;
import Common.Models.MessageModel;

import java.util.ArrayList;
import java.util.List;


public class ServerHandler {
    private static List<MessageModel> messageHistory = new ArrayList<MessageModel>();
    private SocketHandler socket;
    private List<ClientModel> activeConnections = new ArrayList<ClientModel>();

    public ServerHandler(int port) {
        this.socket = new SocketHandler(port);
    }

    public static List<MessageModel> getMessageHistory() {

        return messageHistory;
    }
}
