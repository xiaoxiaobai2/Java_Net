package Demo04_UDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
/**
  * @author:  Zhang
  * @description:
  *
  * 创建服务端
 *  1、创建服务端 +端口
 *  2、准备接受容器
 *  3、打包
 *  4、接受
 *  5、分析
 *  6、释放
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        //1、创建服务端+端口
        DatagramSocket server=new DatagramSocket(6666);
        //2、准备容器
        byte[] bytes=new byte[1024];
        //3、打包
        DatagramPacket packet=new DatagramPacket(bytes,bytes.length);
        //4、接收
        server.receive(packet);
        //5、获取分析数据
        bytes=packet.getData();
        get_data(bytes);

        //6、释放
        server.close();
    }
    /**
      * @author:  Zhang
      * @description:
      *
      * 获取 将文件已字节流的形式传输的数据
     **/
    private static void get_File(byte[] bytes) throws IOException {
        InputStream is=new BufferedInputStream(new ByteArrayInputStream(bytes));
        bytes=is.readAllBytes();
        String s=new String(bytes,0,bytes.length);
        is.close();
        System.out.println(s);
    }
    /**
      * @author:  Zhang
      * @description:
      *
      * 获取 将数据以字节流的形式传输的数据
     **/
    private static void get_data(byte[] bytes) throws IOException {
        DataInputStream dataInputStream=new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(bytes)));
        double d=dataInputStream.readDouble();
        dataInputStream.close();
        System.out.printf("double数据：%-6.2f",d);//%-m.n  加-号，左对齐，共占m位，小数占n位
    }
}
