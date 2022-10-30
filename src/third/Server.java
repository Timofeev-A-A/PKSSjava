package third;

import java.io.*;
import java.net.*;


public class Server extends Thread {
    ServerSocket serverSocket;
    public Server() {
        try {
            serverSocket = new ServerSocket(1001);
            System.out.println(serverSocket.toString());
        } catch (IOException e) {
            fail(e, "Could not start server.");
        }
        System.out.println("Server started . . .");
        this.start();
    }
    public static void fail(Exception e, String str) {
        System.out.println(str + "." + e);
    }
    public void run() {
        try {
            while (true) {
                Socket client = serverSocket.accept();
                Connection con = new Connection(client);
            }
        } catch (IOException e) {
            fail(e, "Not listening");
        }
    }
    public static void main(String args[]) {
        new Server();
    }
}
class Connection extends Thread {
    protected Socket netClient;
    protected BufferedReader fromClient;
    protected PrintStream toClient;
    public Connection(Socket client) {
        netClient = client;
        try {
            fromClient = new BufferedReader
                    (new InputStreamReader(netClient.getInputStream()));
            toClient = new PrintStream(netClient.getOutputStream());
        } catch (IOException e) {
            try {
                netClient.close();
            } catch (IOException e1) {
                System.err.println("Unable to set up streams" + e1);
                return;
            }
        }
        this.start();
        System.out.println("connected to "+netClient);
    }
    public void run() {
        String message, garbage;
        double a, b;
        int n;
        int[] array;
        try {
            while (true) {
                toClient.print("Insert task number (1, 2, 3, 4, 5) or 0 to exit the program: \n");
                message = fromClient.readLine();
                if (message.equals("1")) {
                    toClient.print("Insert N: \n");
                    a = Double.parseDouble(fromClient.readLine());
                    toClient.println("Result: " + task1(a) + " | Insert anything to continue...");
                    garbage = fromClient.readLine();
                }
                if (message.equals("2")) {
                    toClient.print("Insert N: \n");
                    n = Integer.parseInt(fromClient.readLine());
                    toClient.println("Result: " + task2(n) + " | Insert anything to continue...");
                    garbage = fromClient.readLine();
                }
                if (message.equals("3")) {
                    toClient.print("Insert N: \n");
                    n = Integer.parseInt(fromClient.readLine());
                    toClient.println("Result: " + task3(n) + " | Insert anything to continue...");
                    garbage = fromClient.readLine();
                }
                if (message.equals("4")) {
                    toClient.print("Insert N: \n");
                    a = Double.parseDouble(fromClient.readLine());
                    toClient.println("Result: " + task4(a) + " | Insert anything to continue...");
                    garbage = fromClient.readLine();
                }
                if (message.equals("5")) {
                    toClient.print("Insert N: \n");
                    n = Integer.parseInt(fromClient.readLine());
                    toClient.println("Result: " + task5(n) + " | Insert anything to continue...");
                    garbage = fromClient.readLine();
                }
                if (message == null || message.equals("0")) {
                    System.out.println("Exit");
                    break;
                }
            }
        } catch (IOException e) {
        } finally {
            try {
                netClient.close();
            } catch (IOException e) {
            }
        }
    }
    public double task1(double n) {
        double a = 1;
        while (a < n) {
            a = a * 5;
        }
        return a;
    }
    public String task2(int n) {
        int a = n;
        if(n == 0)
            return a + " is not a power of 4";
        while(n != 1)
        {
            if(n % 4 != 0)
                return a + " is not a power of 4";
            n = n / 4;
        }
        return a + " is a power of 4";
    }
    public String task3(int max) {
        double a;
        int n = 1;
        while (true) {
            a = Math.cos(Math.cos(n)/Math.sin(n));
            if (a<0)
                return a+"";
            n+=1;
            if (n > max)
                return "None";
        }
    }
    public double task4(double n) {
        double a = 1;
        while (a < n) {
            a = a * 4;
        }
        return a;
    }
    public String task5(int n) {
        int a = n;
        if(n == 0)
            return a + " is not a power of 5";
        while(n != 1)
        {
            if(n % 5 != 0)
                return a + " is not a power of 5";
            n = n / 5;
        }
        return a + " is a power of 5";
    }
}