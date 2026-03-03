    
package EmailClient;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.*;

public class EmailClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 25;

        try {
            Socket mysocket = new Socket(host, port);

            Scanner Infrom = new Scanner(mysocket.getInputStream());
            DataOutputStream OutTo = new DataOutputStream(mysocket.getOutputStream());

            // read greeting message from the server
            String response = Infrom.nextLine();
            System.out.println("Server: " + response);
            if (!response.startsWith("220")) throw new Exception("220 reply not received from server.");

            // send HELO command
            String message = "HELO usca.edu \r\n";
            System.out.println("Client: " + message);
            OutTo.writeBytes(message);
            response = Infrom.nextLine();   
            System.out.println("Server: " + response);
            if (!response.startsWith("250")) throw new Exception("250 reply not received from server.");

            //mail from
            message = "MAIL FROM:<test@usca.edu>\r\n";
            System.out.println("Client " + message);
            OutTo.writeBytes(message);
            response = Infrom.nextLine();   
            System.out.println("Server: " + response);
            if (!response.startsWith("250")) throw new Exception("250 reply not received from server.");

            //gmail
            message = "RCPT TO:<ayumiapodaca66@gmail.com>\r\n";
            System.err.println("Client " + message);
            OutTo.writeBytes(message);
            response = Infrom.nextLine();   
            System.out.println("Server: " + response);
            if (!response.startsWith("250")) throw new Exception("250 reply not received from server.");

            //data
            message = "DATA\r\n";
             System.err.println("Client " + message);
            OutTo.writeBytes(message);
            response = Infrom.nextLine();   
            System.out.println("Server: " + response);
            if (!response.startsWith("354"))throw new Exception("354 OK, send.");

            message = "From: test@usca.edu\r\n" +
                    "To: ayumiapodaca66@gmail.com\r\n" +
                    "Subject: Nice to meet you\r\n" +
                    "\r\n" +
                    "Hello from my SMTP client!\r\n" +
                    ".\r\n";
            OutTo.writeBytes(message);
            response = Infrom.nextLine();   
            System.out.println("Server: " + response);
            if (!response.startsWith("250"))throw new Exception("250 QUEUED");

            //quit
            message = "QUIT\r\n";
            OutTo.writeBytes(message);
            response = Infrom.nextLine();   
            System.out.println("Server: " + response);
            if (!response.startsWith("250"))throw new Exception("250 QUEUED");


            // close everything
            Infrom.close();
            OutTo.close();
            mysocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}