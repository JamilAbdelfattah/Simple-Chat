package Server;

import Common.Models.ClientModel;
import Common.Models.MessageModel;
import Common.Models.UserModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class SocketHandler {
    private List<ClientModel> acceptedClients = new ArrayList<ClientModel>();
    private static List<MessageModel> messageHistory;
    private ServerSocket serverSocket;

    public SocketHandler(int port) {
        ServerHandler.getMessageHistory();
        bindToPort(port);
    }

    private void bindToPort(int port) {

        try {
            serverSocket = new ServerSocket(port);

            while (true) {
                acceptClients();
                listenOnPort();
            }
        } catch (java.io.IOException e) {
            System.out.println("Server Failed");
        }
    }

    private void acceptClients() {
        try {
            acceptedClients.add(new ClientModel(serverSocket.accept(), (int) Math.random() * 1000, new UserModel("")));
        } catch (java.io.IOException e) {
            System.out.println("Server Failed to accept a client");
        }
    }

    private void listenOnPort() {
        for (ClientModel client : acceptedClients) {
            try {
                JAXBContext context = JAXBContext.newInstance(MessageModel.class, UserModel.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                MessageModel acceptedClientMessage = (MessageModel) unmarshaller.unmarshal(client.getClientDataIn());
                messageHistory.add(acceptedClientMessage);
                broadCast(acceptedClientMessage);
            } catch (JAXBException e) {
                System.out.println("Failed to Receive");
            }
        }
    }

    private void broadCast(MessageModel message) {
        for (ClientModel client : acceptedClients) {
            try {
                JAXBContext context = JAXBContext.newInstance(MessageModel.class, UserModel.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(message, client.getClientDataOut());
            } catch (JAXBException e) {
                System.out.println("Failed to Send");
            }
        }
    }
}
