package com.portfolio.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * 메일 전송을 위한 클래스
 * 다음에서 제공하는 메일서버를 이용하고 있으며
 * 추가적인 사용방법은 검색 ㄱㄱ
 * */
public class MailSendThred extends Thread{

	private String to;
	private String subject;
	private String content;
	
	public MailSendThred(String to, String subject, String content){
		this.to = to;
		this.subject = subject;
		this.content = content;
	}
	
	public static void MailSend(
			String to, String subject, String content){
		
		MailSendThred mst = new MailSendThred(to, subject, content);
		mst.start();
	}
	
	public void run(){
		Properties p = new Properties(); // 정보를 담을 객체
		
		String MAIL_SMTP_CONNECTIONTIMEOUT ="mail.smtp.connectiontimeout";
		String MAIL_SMTP_TIMEOUT = "mail.smtp.timeout";
		String MAIL_SMTP_WRITETIMEOUT = "mail.smtp.writetimeout";
		String MAIL_SOCKET_TIMEOUT = "6000";
		p.put(MAIL_SMTP_CONNECTIONTIMEOUT, MAIL_SOCKET_TIMEOUT);
		p.put(MAIL_SMTP_TIMEOUT, MAIL_SOCKET_TIMEOUT);
		p.put(MAIL_SMTP_WRITETIMEOUT, MAIL_SOCKET_TIMEOUT);
		
		p.put("mail.smtp.host","smtp.daum.net");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		try{
			
			Authenticator auth = new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	            	//인증 이메일로 수정
	            	return new PasswordAuthentication("test@mail.com","password");
	            }
	        };
	        
			Session sess = Session.getInstance(p, auth);
			
			MimeMessage msg = new MimeMessage(sess);
			msg.setSubject(subject, "UTF-8");
			
			//보내는 사람의 이메일 주소
			Address fromAddr = new InternetAddress("test@mail.com");
			msg.setFrom(fromAddr);
			
			Address toAddr = new InternetAddress(to);
			
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			
			msg.setContent(content, "text/html;charset=UTF-8");
			
			Transport.send(msg);
			
		} catch(Exception e){
			System.out.println("exception error");
			e.printStackTrace();
		}
		
	}
}
