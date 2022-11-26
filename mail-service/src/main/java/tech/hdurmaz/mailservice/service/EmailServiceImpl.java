package tech.hdurmaz.mailservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import tech.hdurmaz.mailservice.dto.MailDto;

@Component
@Slf4j
public class EmailServiceImpl implements EmailService {

  @Autowired
  private JavaMailSender emailSender;

  public String sendSimpleMail(MailDto mailDto) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(mailDto.getTo());
      message.setSubject(mailDto.getSubject());
      message.setText(mailDto.getText());
      emailSender.send(message);
      return "Mail gönderildi.";
    } catch (Exception e) {
      log.warn(e.getMessage());
      return e.getMessage();
    }

  }
}
