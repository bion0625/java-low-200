package mySample.stock1.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mySample.stock.SendMail;

public class SendMailService {
    public static void main(String[] args) throws Exception {
        SendMail sendMail = new SendMail();
        sendMail.sendMailByGoogle(
            "보내는 메일 주소", "비밀번호", "받는 메일 주소", "test 제목", "테스트 내용"
        );
    }

    public void sendMailByGoogle(String from, String to, String password, String title, String content) {
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
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(title);
            msg.setText(content);
    
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
