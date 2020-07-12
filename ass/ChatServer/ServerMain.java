import java.io.*;
import java.net.*;
import java.util.Date;

public class ServerMain {

	public static void main(String[] args)throws Exception {
       
		int port = 6916; 
        Server server = new Server(port);
        server.start();
	} 


} 
