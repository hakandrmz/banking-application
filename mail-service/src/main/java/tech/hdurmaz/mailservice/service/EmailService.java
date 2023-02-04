package tech.hdurmaz.mailservice.service;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    void sendEmail(String toList, MultipartFile fileToAttach)
            throws MessagingException, IOException;
}
