package com.example.demo.service;


import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

	@Service
	public class ContactService {
	@Autowired
	ContactRepository repo;
	 public Contact addContact(Contact contact) {
		 Contact savedContact = repo.save(contact);
		 try {
			 sendMail(savedContact);
		 }
		 catch(UnsupportedEncodingException | MessagingException e)
		 {
			 e.printStackTrace();
			 
		 }
		 return savedContact;
	 }
	private void sendMail(Contact savedContact) throws MessagingException,UnsupportedEncodingException{
		JavaMailSenderImpl mailSender= new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("eartheritage2022@gmail.com");
		mailSender.setPassword("eart@2022");
		
		Properties mailProperties =new Properties();
		mailProperties.setProperty("mail.smf.auth", "true");
		mailProperties.setProperty("mail.smt.startls.enable","true");
		mailSender.setJavaMailProperties(mailProperties);
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("eartheritage2022@gmail.com","E-Art Heritage contact Team");
		helper.setTo(savedContact.getEmail());
		helper.setSubject("Thanks for conatcting Slashcode");
		
		String content = "Thanks for contacting us , we will contact you soon. \n Your Request ID: REQ00000"+savedContact.getRequestid();
		
		helper.setText(content, true);

}
	}
