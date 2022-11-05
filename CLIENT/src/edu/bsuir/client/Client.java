package edu.bsuir.client;

import java.io.*;
import java.net.Socket;

public class Client {

    public static final int PORT = 8080;
    public static final String HOST = "localhost";
    private static Client instance;
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;


    private Client() throws IOException {
        clientSocket = new Socket(HOST, PORT);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        System.out.println("Инициализация прошла успешно");
    }

    public static Client getInstance() throws IOException {
        if(instance == null){
            instance = new Client();
        }
        return instance;
    }


    public String dataSendAndTake(String typeOfOperation, String jsonStringobject) throws IOException {
        out.write(typeOfOperation + "\n");
        out.write(jsonStringobject + "\n");
        out.flush();
        String serverAnswer = in.readLine();
        return serverAnswer;
    }

    public void streamsClose() throws IOException {
        clientSocket.close();
        in.close();
        out.close();
    }






}

