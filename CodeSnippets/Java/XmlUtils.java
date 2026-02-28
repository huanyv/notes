
import javax.xml.bind.*;
import java.io.*;

/*
@XmlRootElement(name = "root")
public class ObjectList {
    @XmlElement(name = "msg")
    @XmlElementWrapper(name = "msglist")
    private List<Object> msg;
}
*/

/**
 * JAXB工具类，JDK8测试
 */
public final class XmlUtils {

    private XmlUtils() {
    }

    /**
     * 将Java对象转换成Xml字符串
     */
    public static String toXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            // marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * 将Xml字符串转换成Java对象
     */
    public static <T> T fromXml(Class<T> cls, String xmlStr) {
        T xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(cls);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = (T) unmarshaller.unmarshal(sr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

}
