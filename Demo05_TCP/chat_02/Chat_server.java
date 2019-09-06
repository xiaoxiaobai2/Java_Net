//package Demo05_TCP.chat_02;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.List;
//
///**
//  * @author:  Zhang
//  * @description:
//  *
//  *      与服务器进行聊天
// * 1、建立服务器 设置端口号
// * 2、无限循环，当有新的连接建立时，为其开辟一条新的线程，收发消息
// **/
//
//public class Chat_server {
//    public static void main(String[] args) throws IOException {
//        List<Socket> socketList;//将所有用户存在一个list里方便管理
//        int i=1;
//        //1、创建服务器 指定接口
//        ServerSocket chat_server=new ServerSocket(9999);
//        while (true){
//            //2、建立连接
//            Socket socket=chat_server.accept();
////            Server_Thread server_thread=new Server_Thread(socket);
////            socketList.add(socket);//加入队列，方便管理
//            System.out.printf("成功连接连接第%d个客户端！\n",i);
////            new Thread(new Server_Thread(socket,socketList)).start();
//            i++;
//       }
//    }
//    /**
//     * @author:  Zhang
//     * @description:
//     *
//     *      一个内部类 --  服务器线程
//     *   传入 socket  先获取socket的信息，之后重新建立管道，转发信息
//     *     当连接断开时，输出与客户端已断开连接
//     *     否则重复执行 转发任务
//     *
//     **/
//    private class Server_Thread implements Runnable{
//        private BufferedReader bufferedReader;
//        private BufferedWriter bufferedWriter;
//        private String msg=null;
//        //    private int i;//第几个客户端
//        private boolean isRunning =true;
//
//        public Server_Thread(Socket socket) {
//            try {
//                bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
////            bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            } catch (IOException e) {
//                System.out.println("与第%d个服务器断开连接！\n");
//                isRunning=false;
//                Close_Util.closeAll(bufferedReader,bufferedWriter);
//            }
//        }
//
//        private void receive_msg(){
//            try {
//                msg=bufferedReader.readLine();
//            } catch (IOException e) {
////                System.out.printf("%d号用户退出群聊\n",i);
//                isRunning=false;
//                Close_Util.closeAll(bufferedWriter,bufferedReader);
//            }
//        }
//        //    private void send_msg(){
////        try {
////            String s=bufferedReader.readLine();
////            bufferedWriter.write("服务器向第"+i+"个客户端回复消息 --> "+s);
////            bufferedWriter.newLine();
////            bufferedWriter.flush();
////        } catch (IOException e) {
////            System.out.println("建立连接失败！");
////            isRunning=false;
////            Close_Util.closeAll(bufferedWriter,bufferedReader);
////        }
////    }
//        private void send_msgToOthers(){
//            try {
//                String s=bufferedReader.readLine();
//                bufferedWriter.write("服务器向第"+i+"个客户端回复消息 --> "+s);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//            } catch (IOException e) {
//                System.out.println("建立连接失败！");
//                isRunning=false;
//                Close_Util.closeAll(bufferedWriter,bufferedReader);
//            }
//        }
//
//
//        @Override
//        public void run() {
//            while (isRunning){
////                send_msg();
//            }
//        }
//    }
//}
//
//
//
