package Client;

import Common.Models.MessageModel;
import Common.Models.UserModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;

public class SocketHandler {
    Socket hostSocket;
    UserModel currentUser;

    public SocketHandler(String host, int port, UserModel currentUser) {
        this.currentUser = currentUser;
        connectToHost(host, port);
    }

    private void connectToHost(String host, int port) {
        while(true) {
            try {
                hostSocket = new Socket(host, port);
                reciveMessage();
            } catch (IOException e) {
                System.out.println("Failed to connect to host");
            }
        }
    }

    public void sendMessage(String message) {
        try {
            JAXBContext context = JAXBContext.newInstance(MessageModel.class, UserModel.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(new MessageModel(currentUser, DateFormat.getTimeInstance(), message), hostSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Failed to use OutputStream");
        } catch (JAXBException e){
            System.out.println("Failed to parse object into xml");
        }
    }

    private void reciveMessage() {
        try {
            JAXBContext context = JAXBContext.newInstance(MessageModel.class, UserModel.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MessageModel incommingMessage = (MessageModel) unmarshaller.unmarshal(hostSocket.getInputStream());
            System.out.println(incommingMessage.toString());
        } catch (IOException e) {
            System.out.println("Failed to Receive");

        }catch(JAXBException e){
            System.out.println("Failed to parse incomming message.");
        }

    }
}
