package test;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class MyServer {
    int port;
    ClientHandler ch;
    boolean stop;
    Socket clients;
    public MyServer(int port, ClientHandler ch){
        this.port = port;
        this.ch = ch;
        stop = false;
    }
    public void start(){
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(port);
                server.setSoTimeout(1000);
                while(!stop){
                    try {
                        clients = server.accept();
                        ch.handleClient(clients.getInputStream(), clients.getOutputStream());
                        ch.close();
                        clients.close();
                    }catch(SocketTimeoutException e){}
                }
                server.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    public void close(){
        ch.close();
        try {
            clients.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stop = true;
    }
}
