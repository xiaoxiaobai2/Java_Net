package Demo05_TCP;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client=new Socket("localhost",8888);

//        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
//        System.out.println(bufferedReader.readLine());

        BufferedInputStream bis=new BufferedInputStream(client.getInputStream());


        byte[] car=new byte[1024];
        int len=0;
        while (-1!=(len=bis.read(car,0,1024))){
            System.out.println(new String(car,0,len));
        }


        byte[] bytes=bis.readAllBytes();
        System.out.println(new String(bytes));
//        String s=new String(car);
//        System.out.println(s);
//        bis.close();
        client.close();
    }
}
