package com.member.action;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.gmail.Gmail;
import com.cos.gmail.SHA256;
import com.member.db.MemberDAO;

public class MemberLoginInfoSearch implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("C : MemberLoginInfoSearch_execute() 호출");
		
		String username = request.getParameter("username");	
		String userid = request.getParameter("userid");
				
		String host = "http://localhost:8088/food/";
//		String host = "http://itwillbs11.cafe24.com/food/";   // 팀프로젝트 주소변경....
		
		String from = "ssarmango@gmail.com";
		String to = request.getParameter("email");
		String code = SHA256.getEncrypt(to, "cos");
		System.out.println(to);
		
		MemberDAO mdao = new MemberDAO();
		
		boolean idCheck = mdao.duplicateIdCheck(userid);
		int emailCheck = 0;
		
		// email 존재 확인 작성
		if(userid == null) {
			emailCheck = mdao.getEmailSearch(to);
		} else {			
			emailCheck = mdao.getEmailSearch(userid, to);
		}		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter script = response.getWriter();
		
		if(emailCheck == 1) {
			request.setCharacterEncoding("UTF-8");

			//사용자에게 보낼 메시지
			String subject= "";
			String content = "";
			System.out.println(idCheck);
			if(idCheck) {
				subject = "비밀번호를 찾기 위한 이메일 인증 메일입니다.";
				content = "다음 링크에 접속하여 이메일 인증을 진행해주세요. " 
				        + "<a href='" + host + "MemberEmailCheckAction.me?code=" + code
						+ "&email=" + to + "&userid=" + userid + "'>이메일 인증하기</a>";
			} else {
				subject = "아이디 찾기 위한 이메일 인증 메일입니다.";
				content = "다음 링크에 접속하여 이메일 인증을 진행해주세요. " 
				        + "<a href='" + host + "MemberEmailCheckAction.me?code=" + code
						+ "&email=" + to + "'>이메일 인증하기</a>";
			}
			
			Properties p = new Properties();
			p.put("mail.smtp.user", from);
			p.put("mail.smtp.host", "smtp.googlemail.com");
			p.put("mail.smtp.port", "465"); //TLS 587, SSL 465
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "465"); 
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.sockerFactory.fallback", "false");	
			
			try {
				Authenticator auth = new Gmail();
				Session ses = Session.getInstance(p, auth);
				ses.setDebug(true);
				MimeMessage msg = new MimeMessage(ses);
				msg.setSubject(subject);
				Address fromAddr = new InternetAddress(from);
				msg.setFrom(fromAddr);
				Address toAddr = new InternetAddress(to);
				msg.addRecipient(Message.RecipientType.TO, toAddr);
				msg.setContent(content, "text/html; charset=UTF8");
				Transport.send(msg);
				
				response.setContentType("text/html; charset=UTF-8");
				script = response.getWriter();
				
				script.println("<script>");
				script.println("alert('이메일 주소 인증 메일이 전송되었습니다. 이메일에 들어가서 인증해주세요')");
				script.println("history.back();");
				script.println("</script>");
				
			} catch (Exception e) {			
				script.println("<script>");
				script.println("alert('이메일 인증 오류')");
				script.println("history.back();");
				script.println("</script>");
			}
			
		} else {
			script.println("<script>");
			script.println("alert('이메일이 존재 하지 않습니다.')");
			script.println("history.back();");
			script.println("</script>");
		}
		
		return null;
	}		
}
