package third;

import java.net.*;
import java.io.*;
import java.util.Objects;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket;
        PrintStream out = null;
        BufferedReader in = null;
        try {
            clientSocket = new Socket("0.0.0.0", 1001);
            out = new PrintStream(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unidentified hostname ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O ");
            System.exit(1);
        }
        BufferedReader stdin = new BufferedReader(new InputStreamReader((System.in)));

        while (true) {
            String inMessage = in.readLine();
            System.out.println(inMessage);
            String outMessage = stdin.readLine();
            out.println(outMessage);
            if (Objects.equals(outMessage, "0"))
                break;
        }
        out.close();
        in.close();
        stdin.close();
    }
}