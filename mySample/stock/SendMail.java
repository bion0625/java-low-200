package mySample.stock;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendMail {
    public static void main(String[] args) throws Exception {
        SendMail sendMail = new SendMail();
        sendMail.sendMailByGoogle(
            "보내는 메일 주소", "비밀번호", "받는 메일 주소", "test 제목", "테스트 내용"
        );
    }

    public void sendMailByGoogle(String from, String password, String to, String title, String content) throws Exception {
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(title);
        msg.setText(content);

        Transport.send(msg);
    }
}
