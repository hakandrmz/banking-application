package tech.hdurmaz.mailservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tech.hdurmaz.mailservice.config.MailProperties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final MailProperties mailProperties;

    private Session getSession() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.host", mailProperties.getMail().get("host"));
        props.put("mail.smtp.port", 587);

        return Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProperties.getMail().get("username"), mailProperties.getMail().get("password"));
            }
        });
    }

    public void sendEmail(String toList, MultipartFile fileToAttach) throws MessagingException, IOException {
        Message message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress("hakandrmz18@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toList));

        message.setSubject("SUBJECT");

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("TEXT");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.attachFile(convert(fileToAttach));
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);
        Transport.send(message);
    }

    public File convert(MultipartFile file) {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            convFile.createNewFile();
            fos.write(file.getBytes());
        } catch (IOException e) {
            convFile = null;
        }

        return convFile;
    }
}
