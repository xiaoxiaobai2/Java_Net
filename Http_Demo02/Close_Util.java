package Http_Demo02;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public class Close_Util {
    public static void closeAll(Closeable... io){
        for (Closeable temp:io){
            if (temp!=null){
                try {
                    temp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void closeSocket(Socket socket){
        if (socket!=null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
