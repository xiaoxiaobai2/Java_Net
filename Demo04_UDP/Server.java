package Demo04_UDP;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        int length=packet.getLength();
        InputStream is=new BufferedInputStream(new ByteArrayInputStream(bytes));
        bytes=is.readAllBytes();
        String s=new String(bytes,0,bytes.length);
        System.out.println(s);
        //6、释放
        server.close();
    }
}
