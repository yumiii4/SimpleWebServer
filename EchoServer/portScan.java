
import java.net.Socket;


public class portScan {

    public static void main(String[] args) {
        String host = "student.usca.edu";
        int port[]={22,23,25,80,135,139,443,445};
        System.out.println("Start Scanning");
        for (int p: port){
        try (Socket clientSocket = new Socket(host,p)){
            System.out.println("Port" + p + "is open on" + host);
        }catch (Exception e){
            System.out.println("Port" + p + "is closed+" + host);
       
        }
            

        }

    }
}

    


