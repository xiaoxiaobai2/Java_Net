package Http_Demo02;

import Demo06_Http.Close_Util;
import Demo06_Http.Request;
import Demo06_Http.Response;
import Demo06_Http.ServerLet;

import java.net.Socket;

public class Dispatcher implements Runnable {
    private Request request;
    private Response response;
    private Socket socket;

    public Dispatcher(Socket socket) {
        this.socket = socket;
        this.request = new Request(socket);
        this.response = new Response(socket);
    }
    /**
     *  接收客户端发送的请求  并输出
     */
    private void get_res(){
        System.out.println("获取请求信息");
        request.print_res();
    }

    /**
     * 发送响应信息
     */
    private void sendMsg(){
        //响应内容
        System.out.println("发送响应信息");
        response.print();
    }
    @Override
    public void run() {
        ServerLet serv=new ServerLet();
        serv.server(request,response);
        get_res();
        sendMsg();
        Close_Util.closeSocket(socket);
    }
}
