import java.net.InetAddress;
import java.net.UnknownHostException;

public class Demo01_Inet {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address=InetAddress.getLocalHost();
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());
        System.out.println(address.getAddress().toString());
        System.out.println(address.isLinkLocalAddress());
        System.out.println(address.toString());
        InetAddress address1=InetAddress.getByName("192.168.92.2");
        System.out.println(address1.getAddress());
        System.out.println(address1.getHostName());
        System.out.println(address1.getHostAddress());
        System.out.println("*********************************");

        //字符转为字节
        byte[] bytes=new byte[4];
        String[] strings="39.156.66.14".split("\\.");//追识别 . 要用转义字符\
        for (int i=0;i<4;i++){
            bytes[i]=(byte)(Integer.parseInt(strings[i])&0xff);
        }

        InetAddress address2=InetAddress.getByAddress(bytes);
        System.out.println(address2.getAddress());
        System.out.println(address2.getHostName());
        System.out.println(address2.getHostAddress());
    }
}
