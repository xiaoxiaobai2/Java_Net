package Demo06_Http;

public class ServerLet {
    public void server(Request request,Response response){
        StringBuffer responseContext =new StringBuffer();
        responseContext.append("<html><head><title>HTTP响应示例</title>" +
                "</head><body>");
        responseContext.append("欢迎").append(request.getValuePara("username")).append("访问</body></html>");
        //发送响应信息
        response.setContext(responseContext);
    }
}
