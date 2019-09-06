package Demo05_TCP.chat;

import java.io.Closeable;
import java.io.IOException;

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
}
