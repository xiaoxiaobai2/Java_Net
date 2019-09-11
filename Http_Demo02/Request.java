package Http_Demo02;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.*;

public class Request {
    private String method;
    private Socket socket;
    private String url;
    private String form;//存储所有信息的行
    private Map<String, List<String>> map=new HashMap<>();//存储信息的键值对
    private static final String CRLF="\r\n";
    private InputStream is;
    private String res;

    public Request(Socket socket) {
        this.socket = socket;
        try {
            is=socket.getInputStream();
            get_Res();
        } catch (IOException e) {
            System.out.println("创建输入流失败");
        }
    }
    private void get_Res(){
        byte[] bytes=new byte[20480];
        int len= 0;
        try {
            len = is.read(bytes);
            if (len!=-1){
                res=new String(bytes,0,len);
            }
        } catch (IOException e) {
            System.out.println("读取内容失败");
        }
        get_inf();
    }

    public void print_res(){
        System.out.println(res);
    }
    /**
     * 分析请求信息   获取 method（目前只考虑post 和get 两种请求方式）
     *      获取URL
     *      获取参数信息
     *
     */
    private void get_inf(){
        String line1=res.split(CRLF)[0];//获取第一行
        int index=line1.indexOf("/");//获取第一行的 “/”所在的索引
        method=line1.substring(0,index).trim();//  获取方式 去除空格
        line1=line1.substring(index);
        String urlStr=line1.substring(0,line1.indexOf("HTTP")).trim();
        if(method.equalsIgnoreCase("get")){
            if (urlStr.contains("?")){
                String[] s=urlStr.split("\\?");
                url=s[0];
                form=s[1];
            }else {
                url=urlStr;
            }
        }else{//获取post的URL 和 form
            url=urlStr;
            form=res.substring(res.lastIndexOf(CRLF));
        }

        System.out.println("Method:" + method);
        System.out.println("url: "+ url);
        System.out.println("Form:" +form);
        get_KV();
    }

    private void get_KV(){
        //划分form
        try {
            form= URLDecoder.decode(form,"utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("不存在的编码方式");
        }
        StringTokenizer token=new StringTokenizer(form,"&");
        while (token.hasMoreTokens()){
            String[] k_v=token.nextToken().split("=");
            if (k_v.length==1){
                k_v=Arrays.copyOf(k_v,2);
                k_v[1]=null;
            }
            String key=k_v[0];
            String value=null==k_v[1]?null:k_v[1];
            if (!map.containsKey(key)){
                map.put(k_v[0],new ArrayList<>());
            }
            map.get(k_v[0]).add(value);
        }
    }

    /**
     * 获取单个数据
     * @param name
     * @return
     */

    public String getValuePara(String name){
        String[] s=getValue(name);
        if (s==null){
            return null;
        }else {
            return s[0];
        }
    }

    /**
     * 获取  数据组
     * @param name
     * @return
     */
    public String[] getValue(String name){
        List<String> values=map.get(name);
        if (values==null){
            return null;
        }else {
            return values.toArray(new String[0]);
        }
    }

}
