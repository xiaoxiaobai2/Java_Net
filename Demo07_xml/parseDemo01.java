package Demo07_xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
/**
  * @author:  Zhang
  * @description:
  *
  * 解析xml文件
 **/
public class parseDemo01 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //1、解析工程
        SAXParserFactory factory=SAXParserFactory.newInstance();
        //2、从解析工厂获取一个解析器
        SAXParser parse=factory.newSAXParser();
        //3、编写处理器
        PersonHandler personHandler=new PersonHandler();
        //4、加载文档  注册处理器
        parse.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("Demo07_xml/person.xml")
                ,personHandler);
        personHandler.getPersonList();

    }
}
