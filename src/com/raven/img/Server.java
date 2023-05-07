package com.raven.img;
import java.net.*;
import java.io.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("Servers Starting..");


        RMIServerHandler RMIserver1 = new RMIServerHandler(5022);
        RMIserver1.start();

        RMIServerHandler RMIserver2 = new RMIServerHandler(6022);
        RMIserver2.start();

        SocketServerHandler sserver1 = new SocketServerHandler(4999, 1);
        sserver1.start();

        SocketServerHandler sserver2 = new SocketServerHandler(5999, 2);
        sserver2.start();

        SocketServerHandler sserver3 = new SocketServerHandler(6999, 3);
        sserver3.start();

        SocketServerHandler sserver4 = new SocketServerHandler(7999, 4);
        sserver4.start();

        SocketServerHandler sserver5 = new SocketServerHandler(8999, 5);
        sserver5.start();

        SocketServerHandler sserver6 = new SocketServerHandler(9999, 6);
        sserver6.start();

        SocketServerHandler sserver7 = new SocketServerHandler(10999, 7);
        sserver7.start();

        SocketServerHandler sserver8 = new SocketServerHandler(11999, 8);
        sserver8.start();


    }
}

class RMIServerHandler extends Thread {
    private int portNumber;
    private Registry registry;

    public RMIServerHandler(int portNumber) throws RemoteException {
        this.portNumber = portNumber;
        this.registry = LocateRegistry.createRegistry(this.portNumber);
    }

    public void run() {
        try {
            System.out.println("Starting RMI Server..." + this.registry);
            Filters skeleton = new FiltersImpl();

            Naming.bind("rmi://localhost:" + this.portNumber + "/Filters", skeleton);
            System.out.println("RMI Server at port " + this.portNumber + " is ready !");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class SocketServerHandler extends Thread {

    final int servernum;
    final int portNumber;

    public SocketServerHandler(int portNumber, int number) {
        this.portNumber = portNumber;
        this.servernum = number;
    }

    @Override
    public void run() {
        ServerSocket server_socket = null;
        try {
            server_socket = new ServerSocket(this.portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server " + this.servernum + " with port Number: " + this.portNumber + ", is now running.");
        while (true) {
            Socket s = null;
            try {
                s = server_socket.accept();

                System.out.println("New Client Connected !" + s);

                DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());

                System.out.println("Server " + this.servernum + " : Assigning new Thread for the Client" + s + ".");

                Thread thread = new ClientHandler(s, dataInputStream, dataOutputStream, this.servernum);

                thread.start();
            } catch (Exception e) {
                try {
                    s.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}

class ClientHandler extends Thread {

    public static double[][] kernel_moy = {{1 / 9f, 1 / 9f, 1 / 9f}, {1 / 9f, 1 / 9f, 1 / 9f}, {1 / 9f, 1 / 9f, 1 / 9f}}; // Moyenne
    public static double[][] kernel_sbl1 = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}}; // Sobel
    public static double[][] kernel_sbl2 = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}}; // Sobel Transpose
    public static double[][] kernel_blr = {{1 / 16f, 2 / 16f, 1 / 16f}, {2 / 16f, 4 / 16f, 2 / 16f}, {1 / 16f, 2 / 16f, 1 / 16f}}; // Blur
    public static double[][] kernel_plc = {{1, 1, 1}, {1, 8, 1}, {1, 1, 1}}; // laplacien
    public static double[][] kernel_oln = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}}; // OutLine
    public static double[][] kernel_ved = {{1, 0, -1}, {1, 0, -1}, {1, 0, -1}}; // Vertical Edge
    public static double[][] kernel_hed = {{1, 1, 1}, {0, 0, 0}, {-1, -1, -1}}; // Horizontal Edge

    final DataInputStream DataInput;
    final DataOutputStream DataOutput;
    final Socket socket;
    private double[][] kernel;

    public ClientHandler(Socket socket, DataInputStream DataInput, DataOutputStream DataOutput, int filter) {
        this.socket = socket;
        this.DataInput = DataInput;
        this.DataOutput = DataOutput;
        switch (filter) {
            case 1 -> this.kernel = kernel_moy;
            case 2 -> this.kernel = kernel_sbl1;
            case 3 -> this.kernel = kernel_sbl2;
            case 4 -> this.kernel = kernel_blr;
            case 5 -> this.kernel = kernel_plc;
            case 6 -> this.kernel = kernel_oln;
            case 7 -> this.kernel = kernel_ved;
            case 8 -> this.kernel = kernel_hed;
        }
    }

    @Override
    public void run() {

        String kernel;
        while (true) {
            try {

                // Read Image Dimensions and Create Int Array with said Dimensions
                String dim = this.DataInput.readUTF();


                String[] size = dim.split("-");

                int width = Integer.parseInt(size[0]);
                int height = Integer.parseInt(size[1]);
                int[][] input = new int[width][height];

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        input[i][j] = Integer.parseInt(this.DataInput.readUTF());
                    }
                }

                int[][] resultat = convolution2D(input, this.kernel);


                int disp = 0;

                System.out.print("Sending : █");
                for (int i = 0; i < width; i++) {
                    disp++;
                    if (disp > width / 10) {
                        disp = 0;
                        System.out.print("█");
                    }
                    for (int j = 0; j < height; j++) {
                        this.DataOutput.writeUTF(String.valueOf(resultat[i][j]));
                    }
                }
                System.out.println(".. Done !");

                System.out.println("Output sent !");

                System.out.println("Shutting down Thread Handling Client :" + socket);

                break;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            // closing resources
            this.DataOutput.close();
            this.DataOutput.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int MultiplicationPixel(int[][] image, double[][] kernel, int x, int y) {
        double result = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                result += image[x + i][y + j] * kernel[i + 1][j + 1];
            }
        }
        return (int) Math.round(result);
    }

    public static int[][] convolution2D(int[][] image, double[][] kernel) {
        int width = image.length;
        int height = image[0].length;
        int[][] resultat = new int[width][height];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
                    resultat[i][j] = image[i][j];
                } else {
                    resultat[i][j] = (int) Math.round(MultiplicationPixel(image, kernel, i, j));
                }
            }
        }
        return resultat;
    }
}