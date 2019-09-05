import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Demo03_url {
    public static void main(String[] args) throws IOException {
        URL url=new URL("http://www.baidu.com:80/aa#b?username=zhanghao");
        System.out.println("协议："+url.getProtocol());
        System.out.println("域名："+url.getHost());
        System.out.println("资源："+url.getPath());
        System.out.println("端口：" +url.getPort());
        System.out.println("锚点："+ url.getRef());
        System.out.println("信息："+url.getUserInfo());
        System.out.println("??"+url.getContent());
    }
}
