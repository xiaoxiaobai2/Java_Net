import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class Demo02_InetSocket {
    public static void main(String[] args) throws UnknownHostException {
        InetSocketAddress address=new InetSocketAddress("192.168.92.2",555);
        System.out.println(address.getHostName());
        System.out.println(address.getAddress());
        System.out.println(address.getPort());
        System.out.println(address.getHostString());
        System.out.println("***************************************");
        InetAddress address1=InetAddress.getByName(address.getHostName());
        System.out.println(address1.getHostAddress());//返回地址
        System.out.println(address1.getHostName());//返回本几名
    }
}
