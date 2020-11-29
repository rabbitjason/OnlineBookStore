package bookstore.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.json.Json;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import bookstore.bean.Book;
import bookstore.bean.Order;
import bookstore.factory.DAOFactory;



public class SendHTMLMail {

	private SendHTMLMail() {}
		
	private static SendHTMLMail g = null;
	
	public static synchronized SendHTMLMail getInstance() {
        if (g == null) {
            g = new SendHTMLMail();
        }
        return g;
    }
	
	public void Send(String mailContent) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            //获取模板html文档
            document = reader.read(SendHTMLMail.class.getResource("../res/pageTemplet.html").getPath());
            Element root = document.getRootElement();
            //分别获取id为name、message、time的节点。
            Element name = getNodes(root,"id","name");
            Element message = getNodes(root,"id","message");
            Element time = getNodes(root, "id", "time");
            
            

            //设置收件人姓名，通知信息、当前时间
            Calendar calendar = Calendar.getInstance();
            //time.setText(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
            name.setText("Ping");
            //随便写的
            String mailText = "Thank you for your order #: "+ mailContent + ", we hope you enjoyed shopping with BookShop.";
            message.setText(mailText);
            Element parentNode =message.getParent();
            Element table_obj = parentNode.addElement("table");
            table_obj.addAttribute("border", "1");
            Element tr_obj = table_obj.addElement("tr");
            Element th_obj1 = tr_obj.addElement("td");
            th_obj1.setText("Title");
            
            Element th_obj2 = tr_obj.addElement("td");
            th_obj2.setText("Quantity");
            
            Element th_obj3 = tr_obj.addElement("td");
            th_obj3.setText("Fee (NZD)");
            
            
            List<Order> orders = DAOFactory.getIOrderDAOInstance().findAll(mailContent);
            Book book = null;
    		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String orderTime = null;

            for (Order ov:orders) {
            	book = DAOFactory.getIBookDAOInstance().findById(ov.getBook_id());
				orderTime = formatTime.format(ov.getOrder_time());
				
            	Element tr_obj1 = table_obj.addElement("tr");
            	
            	Element th_obj4 = tr_obj1.addElement("td");
                th_obj4.setText(book.getTitle());
                
                Element th_obj5 = tr_obj1.addElement("td");
                th_obj5.setText(String.valueOf(ov.getQuantity()));
                
                Element th_obj6 = tr_obj1.addElement("td");
                th_obj6.setText(String.valueOf(ov.getFee()));

			}
            
            time.setText(orderTime);


            //保存到临时文件
            FileWriter fwriter = new FileWriter("d:/temp.html");
            XMLWriter writer = new XMLWriter(fwriter);
            writer.write(document);
            writer.flush();
            //读取临时文件，并把html数据写入到字符串str中，通过邮箱工具发送
            FileReader in = new FileReader("d:/temp.html");
            char[] buff = new char[1024*10];
            in.read(buff);
            String str = new String(buff);
            System.out.println(str.toString());

            new MailSender.Builder(str.toString(),"ping_gao_jpi@sina.com").send();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public Element getNodes(Element node, String attrName, String attrValue) {  

        final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性  
        for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性  
            final String name = attr.getName();// 属性名称  
            final String value = attr.getValue();// 属性的值  
            //System.out.println("属性名称：" + name + "---->属性值：" + value);
            if(attrName.equals(name) && attrValue.equals(value)){
                return node;
            }
        }  
        // 递归遍历当前节点所有的子节点  
        final List<Element> listElement = node.elements();// 所有一级子节点的list  
        for (Element e : listElement) {// 遍历所有一级子节点  
            Element temp = getNodes(e,attrName,attrValue);
            // 递归
            if(temp != null){
                return temp;
            };  
        }  

        return null;
    } 
}
