package Demo05_TCP.chat_02;

import java.io.IOException;
import java.net.Socket;

/**
  * @author:  Zhang
  * @description:
  *     聊天小程序 客户端
  * 1、建立客户端
 * 2、为客户端分配两条线程，分别用于接收消息和发送消息
 **/
public class Chat_Client {
    public static void main(String[] args) throws IOException {
        //1、建立客户端 + 指定  服务器   地址 +端口号
        Socket chat_client=new Socket("localhost",9999);
        //2、连接服务器
        new Thread(new Send(chat_client)).start();
        new Thread(new Receive(chat_client)).start();
    }
}
