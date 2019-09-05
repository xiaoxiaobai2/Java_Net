package Demo04_UDP;

import java.io.*;
import java.net.*;

/**
  * @author:  Zhang
  * @description:
  *
  * 客户端创建 DataGramSocket
 * 1、创建客户端假端口
 * 2、准备数据
 * 3、打包
 * 4、发送
 * 5、释放
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        //1、创建客户端 +端口
        DatagramSocket client=new DatagramSocket(8888);
        //2、准备数据
        String msg="UDP协议";
        byte[] bytes;
        InputStream is=new BufferedInputStream(new FileInputStream("C:\\Users\\Administrator\\Desktop\\Test\\码农真苦.txt"));
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] car=new byte[1024];
        int len =0;
        while (-1!=(len=is.read(car,0,1024))){
            byteArrayOutputStream.write(car,0,len);
        }
        bytes=byteArrayOutputStream.toByteArray();
        System.out.println(new String(bytes));
        byteArrayOutputStream.flush();
        is.close();
        byteArrayOutputStream.close();
        //3、打包数据
        DatagramPacket packet=new DatagramPacket(bytes,bytes.length,new InetSocketAddress(InetAddress.getLocalHost(),6666));
        //4、发送数据
        client.send(packet);
        //5、释放
        client.close();
    }
}
