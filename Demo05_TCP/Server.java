package Demo05_TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        while (true){
            Socket socket=server.accept();
            System.out.println("与一个客户端建立了一个连接！");
            String str="欢迎使用！";
//            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            bw.write(str);
//            bw.newLine();
//            bw.flush();
            BufferedOutputStream bos=new BufferedOutputStream(socket.getOutputStream());
            while (true){
                byte[] bytes=str.getBytes();
                bos.write(bytes);
                bos.flush();
            }
//            bos.close();
        }
    }
    private static void send_Data(String str){

    }
}
