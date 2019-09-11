package Demo06_Http;//package Demo06_Http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer{
    public static void main(String[] args) throws IOException {
        HttpServer httpServer=new HttpServer();
        httpServer.start();
    }

    /**
     * 启动HTTP服务器
     */
    private void start() throws IOException {
        //1、创建http服务器 +设定端口号
        ServerSocket httpServer=new ServerSocket(8888);
        //2、与客户端建立连接（无线循环）
        while(true){
            Socket socket =httpServer.accept();
            System.out.println("与客户端建立连接");
            //为客户端创建一条新的线程，并启动
            The the=new The(socket);
            new Thread(the).start();
        }
    }

    /**
     *  接收客户端发送的请求  并输出
     */


    private class The implements Runnable{
        private boolean isRinning=true;
        private InputStream is;
        private OutputStream os;
        private Socket socket;
        public The(Socket socket) {
            this.socket=socket;
        }

        /**
         * 发送响应信息
         */
        private void sendMsg(){
            //响应内容
            StringBuffer responseContext =new StringBuffer();
            responseContext.append("<html><head><title>HTTP响应示例</title>" +
                    "</head><body>你 好!</body></html>");
            //发送响应信息
            Response response=new Response(socket,responseContext);
            response.print();
        }

        @Override
        public void run() {
            Request request=new Request(socket);
            request.print_res();
            sendMsg();
        }
    }
}
