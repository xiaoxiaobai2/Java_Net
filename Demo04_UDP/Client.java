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
        String path="C:\\Users\\Administrator\\Desktop\\Test\\码农真苦.txt";
        double d=3.14;
        byte[] bytes=convert(d);

        //3、打包数据
        DatagramPacket packet=new DatagramPacket(bytes,bytes.length,new InetSocketAddress(InetAddress.getLocalHost(),6666));
        //4、发送数据
        client.send(packet);
        //5、释放
        client.close();
    }
    /**
      * @author:  Zhang
      * @description:
      *
      * 将 double类型的数据转换为字节流
     **/
    private static byte[] convert(Double d) throws IOException {
        byte[] bytes;
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        DataOutputStream dps=new DataOutputStream(new BufferedOutputStream(byteArrayOutputStream));
        dps.writeDouble(d);
        dps.flush();
        bytes=byteArrayOutputStream.toByteArray();
        dps.close();
        byteArrayOutputStream.close();
        return bytes;
    }
    /**
      * @author:  Zhang
      * @description:
      *
      * 将文件内容转换 为字节流
     **/
    private static byte[] convert(String path) throws IOException {
        byte[] bytes;
        InputStream is=new BufferedInputStream(new FileInputStream(path));
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] car=new byte[1024];
        int len =0;
        while (-1!=(len=is.read(car,0,1024))){
            byteArrayOutputStream.write(car,0,len);
        }
        bytes=byteArrayOutputStream.toByteArray();
//        System.out.println(new String(bytes));
        byteArrayOutputStream.flush();
        is.close();
        byteArrayOutputStream.close();
        return bytes;
    }
}
