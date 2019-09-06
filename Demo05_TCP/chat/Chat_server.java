package Demo05_TCP.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
  * @author:  Zhang
  * @description:
  *
  *      与服务器进行聊天
 * 1、建立服务器 设置端口号
 * 2、无限循环，当有新的连接建立时，为其开辟一条新的线程，收发消息
 **/

public class Chat_server {
    public static void main(String[] args) throws IOException {
        List<Socket> socketList=new ArrayList<>();
        int i=1;
        //1、创建服务器 指定接口
        ServerSocket chat_server=new ServerSocket(9999);
        while (true){
           //2、建立连接
           Socket socket=chat_server.accept();
           socketList.add(socket);
           System.out.printf("成功连接连接第%d个客户端！\n",i);
           new Thread(new Server_Thread(socket,i)).start();
           i++;
       }
    }
}
