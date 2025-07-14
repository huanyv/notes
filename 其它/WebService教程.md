# WebService教程

* WebService是http协议请求

## Java

```java
String url = "http://ip:port/xxxx/xxxx/service/xxxxx?wsdl";
String namespace = "namespace";
String method = "method";
String prefix = "pre";

MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
SOAPMessage message = messageFactory.createMessage();
message.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, StandardCharsets.UTF_8.toString());
message.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");

SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
envelope.addNamespaceDeclaration(prefix, namespace);
QName qName = new QName(method);
SOAPBodyElement soapBodyElement = message.getSOAPBody().addBodyElement(qName);

soapBodyElement.addChildElement("arg0").setValue("xml"); // 参数
soapBodyElement.addChildElement("arg1").setValue("key"); // key
String requestBody = SoapClient.toText(message, StandardCharsets.UTF_8); // 获取请求体
System.out.println("requestBody = " + requestBody);
// 发请求
String response = HttpRequest.post(url).setConnectionTimeout(5000).body(requestBody).contentType("text/xml;charset=UTF-8").execute().body();
System.out.println("response = " + response);
```

## 工具类

```java
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SoapClient {

    private String namespace;

    private String method;

    private String prefix;

    private Charset charset;

    private SOAPMessage soapMessage;

    private SOAPElement soapElement;

    private SoapClient(Charset charset) {
        this.charset = charset;
    }

    public static SoapClient create() {
        return create(StandardCharsets.UTF_8);
    }

    public static SoapClient create(Charset charset) {
        SoapClient soapClient = new SoapClient(charset);
        soapClient.setPrefix("");
        MessageFactory messageFactory = null;
        try {
            messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            SOAPMessage soapMessage = messageFactory.createMessage();
            soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, charset.toString());
            soapMessage.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
            soapClient.setSoapMessage(soapMessage);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return soapClient;
    }

    public SoapClient setNamespace(String namespace) {
        this.namespace = namespace;
        try {
            SOAPEnvelope envelope = this.soapMessage.getSOAPPart().getEnvelope();
            envelope.addNamespaceDeclaration(prefix, this.namespace);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SoapClient setMethod(String method) {
        this.method = hasText(prefix) ? prefix + ":" + method : method;
        QName qName = new QName(this.method);
        try {
            this.soapElement = this.soapMessage.getSOAPBody().addBodyElement(qName);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SoapClient setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public SoapClient addParam(String key, String value) {
        try {
            this.soapElement.addChildElement(key).setValue(value);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String text() {
        return toText(this.soapMessage, this.charset);
    }

    public static String toText(SOAPMessage message, Charset charset) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            message.writeTo(out);
        } catch (SOAPException | IOException e) {
            e.printStackTrace();
        }
        String messageToString = "";
        try {
            messageToString = out.toString(charset.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return messageToString;
    }

    public String send(String urlStr) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            // 设置请求方法为POST
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            // 设置内容类型
            urlConnection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            // 发送POST请求必须设置如下两行
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 写入POST数据
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()))) {
            writer.write(text());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 读取响应内容
        StringBuilder response = new StringBuilder("");
        String responseLine = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            while ((responseLine = reader.readLine()) != null) {
                response.append(responseLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    private void setSoapMessage(SOAPMessage soapMessage) {
        this.soapMessage = soapMessage;
    }

    private static boolean hasText(String s) {
        return s != null && s.trim().length() != 0;
    }

    private static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }
}
```

```java
String url = "http://ip:port/xxxx/xxxx/service/xxxxx?wsdl";
String namespace = "namespace";
String method = "method";
String prefix = "pre";
String responseBody = SoapClient.create()
    .setPrefix(prefix).setNamespace(namespace).setMethod(method)
    .addParam("arg0", "xml")
    .addParam("arg1", "key")
    .send(url);
System.out.println("responseBody = " + responseBody);
```

