package Demo05_TCP.chat;

import java.io.*;
import java.net.Socket;

public class Send implements Runnable {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private boolean isRunning =true;

    public Send(){
        bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    }
    public Send(Socket socket){
        this();
        try {
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            System.out.println("建立连接失败！");
            isRunning =false;
            Close_Util.closeAll(bufferedReader,bufferedWriter);
        }
    }
    private void send(){
        String s= null;
        try {
            s = bufferedReader.readLine();
            bufferedWriter.write(s);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("建立连接失败！");
            isRunning =false;
            Close_Util.closeAll(bufferedReader,bufferedWriter);
        }
    }


    @Override
    public void run() {
        while (isRunning){
            send();
        }
    }
}
