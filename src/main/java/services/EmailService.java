
package services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import domain.Customer;
import domain.PetShipper;
import domain.PetSitter;
import domain.Supplier;
@Service
@Transactional
public class EmailService {
	public void send(String emailRecipient, String content, String subject) {

		try {

			Properties p;
			p = new Properties();
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.setProperty("mail.smtp.starttls.enable", "true");
			p.setProperty("mail.smtp.port", "587");

			p.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(p,
					new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"petcaresepp@gmail.com", "sepp1pet");
						}

					});
			MimeMessage email = new MimeMessage(session);
			email.setFrom(new InternetAddress("petcaresepp@gmail.com"));
			email.addRecipient(Message.RecipientType.TO, new InternetAddress(
					emailRecipient));
			email.setSubject(subject);
			// email.setText("<a href='#'>asad</a>Holaaaa");
			email.setContent(content, "text/html");
			Transport t = session.getTransport("smtp");

			Transport.send(email);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendToAFriend(Supplier sender, String recipient) {
		String url;
		// url = "http://www.acme.com/single/register.do?invitationCode="
		// + sender.getUserCode();
		url = "http://petcare-petcaresepp.rhcloud.com/petSitter/create.do?invitationCode="
				+ sender.getInvitationCode();

		String content;
		String subject;
		subject = "Invitation to Pet Care";

		content = "Hello, "
				+ sender.getName()
				+ " "
				+ sender.getSurname()
				+ " has invited you to join Pet Care as pet sitter. Please,click the link below to join if you want.";
		content += "</br><a href='" + url + "'>Click here</a><br>";

		send(recipient, content, subject);
	}
	public void sendToPetShipper(Supplier sender, String recipient) {
		String url;
		// url = "http://www.acme.com/single/register.do?invitationCode="
		// + sender.getUserCode();
		url = "http://petcare-petcaresepp.rhcloud.com/petShipper/create.do?invitationCode="
				+ sender.getInvitationCode();

		String content;
		String subject;
		subject = "Invitation to Pet Care";

		content = "Hello, "
				+ sender.getName()
				+ " "
				+ sender.getSurname()
				+ " has invited you to join Pet Care as pet shipper. Please,click the link below to join if you want.";
		content += "</br><a href='" + url + "'>Click here</a><br>";

		send(recipient, content, subject);
	}
	
	public void sendToCompany(Administrator sender, String recipient) {
		String url;
		// url = "http://www.acme.com/single/register.do?invitationCode="
		// + sender.getUserCode();
		url = "http://petcare-petcaresepp.rhcloud.com/company/create.do?invitationCode="
				+ sender.getUser().getUsername();

		String content;
		String subject;
		subject = "Invitation to Pet Care";

		content = "Hello, "
				+ sender.getName()
				+ " "
				+ sender.getSurname()
				+ " has invited you to join Pet Care as company. Please,click the link below to join if you want.";
		content += "</br><a href='" + url + "'>Click here</a><br>";

		send(recipient, content, subject);
	}

}


