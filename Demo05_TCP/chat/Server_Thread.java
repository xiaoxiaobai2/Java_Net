package Demo05_TCP.chat;

import java.io.*;
import java.net.Socket;

/**
 * @author:  Zhang
 * @description:
 *     服务器线程
 *   传入 socket  先获取socket的信息，之后重新建立管道，转发信息
 *     当连接断开时，输出与客户端已断开连接
 *     否则重复执行 转发任务
 *
 **/
public class Server_Thread implements Runnable{
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String msg=null;
    private int i;//第几个客户端
    private boolean isRunning =true;

    public Server_Thread(Socket socket, int i) {
        this.i=i;
        try {
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.printf("与第%d个服务器断开连接！\n",i);
            isRunning=false;
            Close_Util.closeAll(bufferedReader,bufferedWriter);
        }
    }

    private void send_msg(){
        try {
            String s=bufferedReader.readLine();
            bufferedWriter.write("服务器向第"+i+"个客户端回复消息 --> "+s);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("建立连接失败！");
            isRunning=false;
            Close_Util.closeAll(bufferedWriter,bufferedReader);
        }
    }


    @Override
    public void run() {
        while (isRunning){
            send_msg();
        }
    }
}
