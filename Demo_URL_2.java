import java.io.*;
import java.net.URL;
/**
  * @author:  Zhang
  * @description:
  *
  * 通过url获取资源 并转存
 **/
public class Demo_URL_2 {
    public static void main(String[] args) throws IOException {
        URL url=new URL("http://www.baidu.com");
        InputStream inputStream=url.openStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu.html"),"utf-8"));
        String s;
        while ((s=bufferedReader.readLine())!=null){
            writer.write(s);
            writer.newLine();
        }
        writer.flush();
        bufferedReader.close();
        writer.close();
    }
}
