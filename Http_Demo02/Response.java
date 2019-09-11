package Http_Demo02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Date;

public class Response {
    private StringBuffer headInf;//存储头信息
    private StringBuffer context;//存储正文信息
    private int len;//保存正文长度
    private static final String CRLF="\r\n";
    private static final String BLANK=" ";
    private BufferedWriter bw;
    private int statusNum=404;//状态码
    private Socket socket;
    private OutputStream os;

    public Response(Socket socket){
        //分配空间
        headInf=new StringBuffer();
        context=new StringBuffer();
        this.socket=socket;
    }

    public Response(Socket socket,StringBuffer context) {
        this(socket);
        this.socket=socket;
        this.context=context;
        this.len=context.toString().getBytes().length;
    }

    private void createHead(){
        headInf=new StringBuffer();
        //1)  返回状态码
        headInf.append("HTTP/1.1").append(BLANK);
        switch (statusNum){
            case 200:headInf.append("200").append(BLANK).append("OK").append(CRLF);
            break;
            case 404:headInf.append("404").append(BLANK).append("Not Found").append(CRLF);
            break;
            case 500:headInf.append("500").append(BLANK).append("Server error").append(CRLF);
            break;
        }
        //2)  请求头(Response Head)
        headInf.append("Server:HttpServer Server/0.0.1").append(CRLF);
        headInf.append("Date:").append(new Date()).append(CRLF);
        headInf.append("Content-type:text/html;charset=utf-8").append(CRLF);
        // 3） 正文长度  字节长度
        headInf.append("Content-Length:").append(len).append(CRLF);
        //4） 空行
        headInf.append(CRLF);
    }
    public void setContext(StringBuffer context){
        this.context =context;
        this.len=context.toString().getBytes().length;
    }

    public void print(){
        try {
            os=socket.getOutputStream();
        } catch (IOException e) {
            System.out.println("创建输出流失败");
            statusNum=500;
            context=null;
        }
        createHead();
        byte[] bytes=null;
        try {
            bytes=headInf.append(context).toString().getBytes("utf-8");
//            System.out.println(headInf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            os.write(bytes);
//            os.flush();
//            Close_Util.closeAll(os);
        } catch (IOException e) {
            System.out.println("输出失败！");
        }
    }
}
