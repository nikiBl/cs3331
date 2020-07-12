import java.io.*;
import java.net.*;

public class ChatClient {

    private final String serverName;
	private final int serverPort;
    private Socket socket;
    private InputStream serverIn;
    private OutputStream serverOut;
    private BufferedReader bufferedIn;
		//change above port number if required


    public ChatClient(String serverName, int serverPort){
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

	public static void main(String[] args) throws Exception {

		// Define socket parameters, address and Port No
		
		
		// create socket which connects to server
		ChatClient client = new ChatClient("localhost", 6916);
        if(!client.connect()){
            System.err.println("connect failed.");
        } else {
            System.out.println("Connect successful");
            if(client.login("guest", "guest")){
                System.out.println("Login successful");
            } else {
                System.err.println("Login failed");
            }
        }

	} // end of main

    public boolean login(String login, String password) throws IOException{
        String cmd = "login"  + login+ " "+ password + "\n";
        serverOut.write(cmd.getBytes());
        String response = bufferedIn.readLine();
        System.out.println(response);

        if("ok login".equalsIgnoreCase(response)){
            return true;
        } else {
            return false;
        }
        
    }

    private boolean connect(){
        try{
            this.socket = new Socket(serverName,serverPort);
            System.out.println("Client port is" + socket.getLocalPort());
            this.serverOut = socket.getOutputStream();
            this.serverIn = socket.getInputStream();
            this.bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

} // end of class TCPClient
