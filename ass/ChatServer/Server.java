import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server extends Thread{
    private final int serverPort;

    private ArrayList<ServerWorker> workerList = new ArrayList<>();
    public Server(int serverPort){
        this.serverPort = serverPort;
    }

    public List<ServerWorker> getWorkerList() {
        return workerList;
    }
    public void run(){
        try{
		    ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server is ready :");

		    while (true){

		        // accept connection from connection queue

                System.out.println("About to accept client connection...");
		        Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from" + clientSocket);
               
                ServerWorker worker = new ServerWorker(this, clientSocket);
                workerList.add(worker);
                worker.start();

                
                
               
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void removeWorker(ServerWorker serverWorker){
        workerList.remove(serverWorker);
    }
    

}
