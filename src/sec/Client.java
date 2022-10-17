package sec;

import java.net.*;
import java.io.*;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket;
        PrintStream out = null;
        BufferedReader in = null;
        try {
            clientSocket = new Socket("0.0.0.0", 1001);
            /* Создается выходной поток, чтобы посылать данные на
            сервер */
            out = new PrintStream(clientSocket.getOutputStream());
/* Создается входной поток, чтобы принимать данные с
сервера */
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unidentified hostname ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O ");
            System.exit(1);
        }
        /* Создается входной поток, чтобы читать данные из окна консоли
         */
        BufferedReader stdin = new BufferedReader(new InputStreamReader((System.in)));
        /* Чтение из сокета */

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

