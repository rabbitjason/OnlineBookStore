package bookstore.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailSender {
    private final Properties prop = new Properties();
    private final Session session;
    private final Message msg;
    private final Transport transport;

    //构建器
    public static class Builder{
        private final String mailContent;
        private final String toAddress;

        private String debug = "true";
        private String auth = "true";
        private String host = "smtp.qq.com";
        private String protocol = "smtp";

        private String subject = "Order Confirmation";
        //发件人地址
        private String fromAddress= "491643804@qq.com";
        //发件账户密码
        private String fromAccount = "491643804";
        private String fromPassword = "wwiueanlmfbxcaid";
        public Builder Debug(String debug) {
            this.debug = debug;
            return this;
        }
        public Builder Subject(String subject) {
            this.subject = subject;
            return this;
        }
        public Builder Auth(String auth) {
            this.auth = auth;
            return this;
        }
        public Builder Host(String host) {
            this.host = host;
            return this;
        }
        public Builder FromCount(String fromCount) {
            this.fromAccount = fromCount;
            return this;
        }
        public Builder FromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
            return this;
        }
        public Builder FromPassword(String fromPassword) {
            this.fromPassword = fromPassword;
            return this;
        }
        public Builder(String mailContent, String toAddress) {
            this.mailContent = mailContent;
            this.toAddress = toAddress;
        }
        public Builder Protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }
        public MailSender send() throws Exception{
            return new MailSender(this);
        }

    }
    private MailSender(Builder builder) throws Exception{
        prop.setProperty("mail.debug", builder.debug);
        prop.setProperty("mail.smtp.auth", builder.auth);
        prop.setProperty("mail.host", builder.host);
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.transport.protocol",builder.protocol);
        //开启ssl加密（并不是所有的邮箱服务器都需要，但是qq邮箱服务器是必须的）
        MailSSLSocketFactory msf= new MailSSLSocketFactory();
        msf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory",msf);
        

        
      //获取Session会话实例（javamail Session与HttpSession的区别是Javamail的Session只是配置信息的集合）
//        session = Session.getInstance(prop,new javax.mail.Authenticator(){
//                protected PasswordAuthentication getPasswordAuthentication(){
//                        //用户名密码验证（取得的授权吗）
//                        return new PasswordAuthentication ("491643804@qq.com", "bfsxxfhuswwzbhia");
//                }
//        });
        
        // 创建session
        session = Session.getInstance(prop);

        msg = new MimeMessage(session);
        msg.setSubject(builder.subject);
        msg.setFrom(new InternetAddress(builder.fromAddress,"COMPX576_BOOKSHOP"));
        
        transport = session.getTransport(); 
        transport.connect("smtp.qq.com", builder.fromAddress, builder.fromPassword);
        msg.setContent(builder.mailContent, "text/html;charset=utf-8");
        transport.sendMessage(msg, new Address[] {new InternetAddress(builder.toAddress)});
        transport.close();
    }

}
