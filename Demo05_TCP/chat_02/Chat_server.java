package Demo05_TCP.chat_02;

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
    private List<Server_Thread> all=new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Chat_server chat_server=new Chat_server();
        chat_server.start();
    }
    private void start() throws IOException {
        //1、创建服务器 指定接口
        ServerSocket chat_server = new ServerSocket(9999);
        while (true) {
            //2、建立连接
            Socket socket = chat_server.accept();
            System.out.println("系统提示："+socket.toString()+"加入群聊");
            Server_Thread server_thread = new Server_Thread(socket);
            all.add(server_thread);//加入队列，方便管理
            //为该服务器开辟一个新的线程
            new Thread(server_thread).start();
        }
    }
    /**
     * @author:  Zhang
     * @description:
     *
     *      一个内部类 --  服务器线程
     *   传入 socket  先获取socket的信息，之后重新建立管道，转发信息
     *     当连接断开时，输出与客户端已断开连接
     *     否则重复执行 转发任务
     *
     **/
    private class Server_Thread implements Runnable{
        private BufferedReader bufferedReader;
        private BufferedWriter bufferedWriter;
        private String msg=null;
        private Socket socket;
        private boolean isRunning =true;

        public Server_Thread(Socket socket) {
            this.socket=socket;
            try {
                //和服务器建立建立连接  接收和发送在这个客户端的消息
                bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                msg="系统消息：欢迎"+socket.toString()+"加入群聊";
                send_msgToOthers();
            } catch (IOException e) {
                //客户端断开连接，系统提示
                breakdown();
            }
        }

        /*
             接收某个客户端发来的消息
         */
        private void receive_msg(){
            try {
                msg=bufferedReader.readLine();
//                for (int i=0;i<msg.length();i++)
//                    System.out.println(msg.charAt(i));
                if (null==msg||(msg.length()==0)){
                }else if (msg.equals("exit")){
                    breakdown();
                }else {
                    msg=socket.toString()+"的消息:"+msg;
                    send_msgToOthers();
                }
            } catch (IOException e) {
                breakdown();
            }
        }

        /*
            想通过服务器向自己发送消息，主要是供其他通道调用
         */
        private void send_msg(String msg){
            try {
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                //客户端断开连接，系统提示
                breakdown();
            }
        }
        /*
            把从客户端接收到的信息转发给其他所有客户端（若有@字符则转给指定客户端）
         */
        private void send_msgToOthers(){
//            while (all.size()!=0){
                for (Server_Thread s:all){
                    if (!s.equals(this)){
                        //向其他所有客户端发送消息
                        s.send_msg(msg);
                    }
                }
//            }
        }
        /*
            断开连接之后要
                移除列表，
                向其他客户端发送消息，
                断开和系统的连接
                重置循环标志 isrunning
         */
        private void breakdown(){
            all.remove(this);//移出列表
            msg=socket.toString()+"退出群聊！";//重置消息
            send_msgToOthers();
            Close_Util.closeAll(bufferedReader,bufferedWriter);
            isRunning=false;
        }


        @Override
        public void run() {
            while (isRunning){
                receive_msg();
            }
        }
    }
}



