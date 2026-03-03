package EmailClient;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class webserver{
    public static void main(String[] args) throws Exception{
       try (ServerSocket server = new ServerSocket(80)) {
            System.out.println("Web server is running on port 80...");
            while (true) {
                // wait for a connection
                Socket client = server.accept();
                Scanner infrom = new Scanner(client.getInputStream());
                DataOutputStream outTo = new DataOutputStream(client.getOutputStream());

                //get the request line of the HTTP request message
                String requestLine = infrom.nextLine();
                String[] tokens = requestLine.split(" ");
                System.out.println("Request: " + requestLine);
                System.out.println("Request method: " + tokens[0]);
                System.out.println("Request URI: " + tokens[1]);
                // construct the response message
                String statusLine = "HTTP/1.0 200 OK\r\n";
                String headlines = "Content-Type: text/html\r\n\r\n";  // finish the response message
                String body = "<html><h1>Yilian Zhang Web Server is working</h1></html>";
                String response = statusLine + headlines + body;
                // send the response message
                outTo.writeBytes(response);
                outTo.flush();
                //close everything
                infrom.close();
                outTo.close();
                client.close();
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
