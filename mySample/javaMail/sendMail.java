package mySample.javaMail;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class sendMail {
    public static void main(String[] args) throws Exception {

        /*
         * 네이버 이메일 설정
         * 메일설정 > POP3/IMAP 설정 > IMAP/SMTP 설정: IMAP/SMTP 설정 사용함 [check]
        */

        String from = "@gmail.com"; // 보내는 사람의 이메일 주소
        String password = ""; // 보내는 사람의 이메일 계정 비밀번호
        String to = "@gmail.com"; // 받는 사람의 이메일 주소
        String host = "smtp.gmail.com"; // 구글 메일 서버 호스트 이름

        // SMTP 프로토콜 설정
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");

        // 보내는 사람 계정 정보 설정
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // 메일 내용 작성
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject("메일 보내기 테스트 타이틀");
        msg.setText("내용몬");

        // 메일 보내기
        Transport.send(msg);
    }
}
