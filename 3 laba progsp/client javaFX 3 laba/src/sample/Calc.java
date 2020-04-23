package sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Calc {

    public String result(byte a, byte b, byte c) throws IOException {
        InetAddress localhost = InetAddress.getByName("localhost");
        DatagramSocket client = new DatagramSocket(4001);

        byte[] sendData = new byte[]{a, b, c};
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, localhost, 4000);
        client.send(sendPacket);

        byte[] receivedData = new byte[100];
        DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);

        client.receive(receivedPacket);
        receivedData = receivedPacket.getData();

        String result = new String(receivedData);

        client.close();
        return result;
    }

}





