import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Servemain {
    private static double sum1, sum2 = 0;
    public static void main(String[] arg) throws IOException, InterruptedException {

        InetAddress localhost = InetAddress.getByName("localhost");
        DatagramSocket server = new DatagramSocket(4000, localhost);

        byte[] buf = new byte[3];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        System.out.println("Server is working");
        Thread t1, t2;
        while (true) {
            server.receive(packet);
            buf = packet.getData();
            int port = packet.getPort();
            System.out.println("Получили инфу с порта " + port);
            byte a = buf[0];
            byte b = buf[1];
            byte c = buf[2];
            System.out.println("Получено " + a + " " + b + " " + c);

            t1 = new Thread(() -> {
                for (int n = a; n <= b; n++) {
                    sum1 += n / (Math.pow(n, 2) + n - 1);
                }
            });
            t2 = new Thread(() -> {
                for (int n = b; n <= c; n++) {
                    sum2 += (Math.pow(n, 2) - 5);
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            double result = sum1 + sum2;
            sum1 = 0.0;
            sum2 = 0.0;
            System.out.println("Результат вычислений: " + result);
            buf = String.valueOf(result).getBytes();
            packet = new DatagramPacket(buf, buf.length, localhost, port);
            server.send(packet);
        }
    }
}


