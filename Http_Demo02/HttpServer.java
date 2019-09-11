package Http_Demo02;//package Demo06_Http;

import Demo06_Http.Dispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private ServerSocket server;
    private Socket client;
    private boolean isRunning=true;
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = new HttpServer();
        httpServer.start();
    }

    /**
     * 启动HTTP服务器
     */
    private void start() throws IOException {
        //1、创建http服务器 +设定端口号
        this.start(8888);
    }
    /**
     *  指定端口号创建服务器，接收客户端
     */
    private void start(int port){
        try {
            server=new ServerSocket(port);
            this.receive();
        } catch (IOException e) {
            System.out.println("端口被占用");
            stop();
        }
    }

    /**
     * 和客户端建立连接，并为其开辟一条线程
     */
    private void receive(){
        try {
            while (isRunning){
                Socket socket=server.accept();
                System.out.println("与客户端建立连接");
                Dispatcher dispatcher = new Dispatcher(socket);
                new Thread(dispatcher).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void stop(){
        isRunning=false;
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}