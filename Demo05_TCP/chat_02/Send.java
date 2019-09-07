package Demo05_TCP.chat_02;

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
            breakdown();
        }
    }
    private void send(){
        String s= null;
        try {
            s = bufferedReader.readLine();
            bufferedWriter.write(s);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            if (s.equals("exit")){
                //手动断开连接
                breakdown();
            }
        } catch (IOException e) {
            breakdown();
        }
    }
    /*
        断开连接
     */
    private void breakdown(){
        System.out.println("与服务器断开连接");
        isRunning =false;
        Close_Util.closeAll(bufferedReader,bufferedWriter);
    }


    @Override
    public void run() {
        while (isRunning){
            send();
        }
    }
}
