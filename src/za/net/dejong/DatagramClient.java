package za.net.dejong;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class DatagramClient
{
    public static void main(String args[]) throws Exception
    {
        System.out.println("Started Datagram Echo Client...");

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");

        String message = "Hello, the password is 's3cr3t007'.\n";
        byte[] sendData = message.getBytes(StandardCharsets.US_ASCII);

        System.out.println("Sending message to server...");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5006);
        clientSocket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        System.out.println("Waiting for server to confirm message...");
        clientSocket.receive(receivePacket);
        String messageFromServer = new String(receivePacket.getData(), StandardCharsets.US_ASCII).trim();
        System.out.println("Message from server: " + messageFromServer);

        clientSocket.close();
    }
}