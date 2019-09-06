package Demo05_TCP.chat;

import java.io.*;
import java.net.Socket;

public class Receive implements Runnable {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private boolean isRunning =true;

    public Receive(){
        bufferedWriter=new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public Receive(Socket socket){
        this();
        try {
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            System.out.println("建立连接失败！");
            isRunning =false;
            Close_Util.closeAll(bufferedReader,bufferedWriter);
        }
    }
    private void receive(){
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
            receive();
        }
    }
}
