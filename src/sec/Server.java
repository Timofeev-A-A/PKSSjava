package sec;

import java.io.*;
import java.net.*;
import java.util.Arrays;

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
                toClient.print("Insert task number (24, 27, 30, 3, 6) or 0 to exit the program: \n");
                message = fromClient.readLine();
                if (message.equals("24")) {
                    toClient.print("Insert S: \n");
                    a = Double.parseDouble(fromClient.readLine());
                    toClient.print("Insert T: \n");
                    b = Double.parseDouble(fromClient.readLine());
                    toClient.println("Result: " + task24(a, b) + " | Insert anything to continue...");
                    garbage = fromClient.readLine();
                }
                if (message.equals("27")) {
                    toClient.print("Insert N: \n");
                    n = Integer.parseInt(fromClient.readLine());
                    toClient.print("Insert array divided by space: \n");
                    array = Arrays.stream(fromClient.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    toClient.println("Result: " + task26(n, array) + " | Insert anything to continue...");
                    garbage = fromClient.readLine();
                }
                if (message.equals("30")) {
                    toClient.print("Insert S: \n");
                    a = Double.parseDouble(fromClient.readLine());
                    toClient.print("Insert T: \n");
                    b = Double.parseDouble(fromClient.readLine());
                    toClient.println("Result: " + task30(a, b) + " | Insert anything to continue...");
                    garbage = fromClient.readLine();
                }
                if (message.equals("3")) {
                    toClient.print("Insert S: \n");
                    a = Double.parseDouble(fromClient.readLine());
                    toClient.print("Insert T: \n");
                    b = Double.parseDouble(fromClient.readLine());
                    toClient.println("Result: " + task3(a, b) + " | Insert anything to continue...");
                    garbage = fromClient.readLine();
                }
                if (message.equals("6")) {
                    toClient.print("Insert A: \n");
                    a = Double.parseDouble(fromClient.readLine());
                    toClient.print("Insert B: \n");
                    b = Double.parseDouble(fromClient.readLine());
                    toClient.println("Result: " + task6(a, b) + " | Insert anything to continue...");
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
    public double task24(double s, double t) {
        return task24G(12, s) + task24G(t, s) - task24G(2*s-1,s*t);
    }
    public double task24G(double a, double b) {
        return ((2*a)+(b*b))/((a*b*2)+(b*5));
    }
    public int task26(int n, int[] a) {
       int sum = 0;
       if (n > a.length)
           n = a.length;
       for (int j=0; j < n; j++) {
           sum+=a[j];
       }
       return sum;
    }
    public double task30(double s, double t) {
        return task30G(12, s) + task30G(t, s) - task30G(2*s - 1, s*t);
    }
    public double task30G(double a, double b) {
        return ((2*a)+(b*b))/((a*b*2)+(b*5));
    }
    public double task3(double s, double t) {
        return task3F(t, -2*s, 1.17)+task3F(2.2, t, s-t);
    }
    public double task3F(double a, double b, double c) {
        return (2*a -b - Math.sin(c))/(5 + c);
    }
    public double task6(double a, double b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }
}

