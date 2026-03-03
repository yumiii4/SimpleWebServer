import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class tcp {
	public static void main(String[] args) throws Exception {
		//remote host and port?
		String host ="localhost";
		int port =4343;
		 
		//create socket to connect to server
		Socket clientSocket = new Socket(host,port);
		//input & output stream
		Scanner inFServer = new Scanner(clientSocket.getInputStream());
		DataOutputStream outTServer = new DataOutputStream(clientSocket.getOutputStream());
		Scanner inFromUser = new Scanner(System.in);

		String message;

		//while loop
		while (true) {
			System.out.println("Enter message ( type QUIT to EXIT):");
			message = inFromUser.nextLine();
		
			if (message.equalsIgnoreCase("QUIT")){
				break;
			}


		}
		//communicate with server

		//System.out.println("Enter Message:");
		//canner inFromUser = new Scanner(System.in);
		//sting message ="Yumi, I am a client \r\n";
		//System.out.println("Client:" + message);

	
		
		//send message to server
		//outTServer.writeBytes(message);
		//outTServer.flush();
		//read response from server
		//String response = inFServer.nextLine();
		//System.out.println("Server response:"+ response);
		
		//close packet
		inFromUser.close();
		outTServer.close();
		inFServer.close();
		clientSocket.close();
		
		
		
		
		
		
		
	}

}