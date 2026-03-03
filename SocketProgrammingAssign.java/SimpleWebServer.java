import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class SimpleWebServer {
    public static void main(String[] args) throws Exception {

        try (ServerSocket server = new ServerSocket(8080)) {   
            System.out.println("Web server is running on port 8080...");

            while (true) {

                Socket client = server.accept();
                Scanner infrom = new Scanner(client.getInputStream());
                DataOutputStream outTo = new DataOutputStream(client.getOutputStream());

                // Get request line
                String requestLine = infrom.nextLine();
                String[] tokens = requestLine.split(" ");

                System.out.println("Request: " + requestLine);

                String method = tokens[0];

                String statusLine;
                String headers = "Content-Type: text/html\r\n\r\n";
                String body = "<html><h1>Dummy Web Server is working</h1></html>";

                if (method.equals("GET") || method.equals("HEAD")) {

                    statusLine = "HTTP/1.0 200 OK\r\n";

                    if (method.equals("HEAD")) {
                        body = "";  
                    }

                } else {
                    statusLine = "HTTP/1.0 400 Bad Request\r\n";
                    body = "<html><h1>400 Bad Request</h1></html>";
                }

                String response = statusLine + headers + body;

                outTo.writeBytes(response);
                outTo.flush();

                infrom.close();
                outTo.close();
                client.close();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
