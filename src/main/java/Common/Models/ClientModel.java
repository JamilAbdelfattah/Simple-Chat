package Common.Models;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientModel {
    private Socket clientSocket;
    private InputStream clientDataIn;
    private OutputStream clientDataOut;
    private UserModel user;
    private int id;

    public ClientModel(Socket clientSocket, int id, UserModel user) {
        this.clientSocket = clientSocket;
        try {
            this.clientDataIn = clientSocket.getInputStream();
            this.clientDataOut = clientSocket.getOutputStream();
            this.user = user;
            this.id = id;
        } catch (IOException e) {
            System.out.println("Could not get Input");
        }

        this.id = id;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public InputStream getClientDataIn() {
        return clientDataIn;
    }

    public OutputStream getClientDataOut() {
        return clientDataOut;
    }

    public int getId() {
        return id;
    }
}
