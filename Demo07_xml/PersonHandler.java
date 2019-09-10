package Demo07_xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
  * @author:  Zhang
  * @description:
  *
  * 从xml文件中获取需要的信息
 **/
public class PersonHandler extends DefaultHandler {
    private Person person;
    private List<Person> personList;
    private String tag;//保存标签名
    @Override
    public void startDocument() throws SAXException {
        System.out.println("开始处理文档");
        personList=new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("文档处理结束");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (null!=qName)
            tag=qName;
        if (qName!=null&&qName.equals("person")){
            person=new Person();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName!=null&&qName.equals("person")){
            personList.add(person);
        }
        tag=null;// 注意每次结束一个标签就把TAG置为空
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str =new String(ch,start,length);
        if (null!=tag&&tag.equals("name")){
            person.setName(str);
        }else if (null!=tag&&tag.equals("age")){
            int age=Integer.parseInt(str);
            person.setAge(age);
        }
    }

    public void getPersonList() {
        for (Person p:personList){
            System.out.println("Name --> " +p.getName());
            System.out.println("Age --> " +p.getAge());
        }
    }
}
