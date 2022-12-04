import java.net.*;
import java.io.*;
import java.util.*;

public class client {

    public static void main(String [] args) {
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));

        String serverName = "172.15.4.166";
        int port = Integer.parseInt("8000");
        boolean mycon = true;
        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            while (mycon){

                String s_to_send = br.readLine();

                System.out.println("sending " + s_to_send);

                out.writeUTF(s_to_send);
            }

            client.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
