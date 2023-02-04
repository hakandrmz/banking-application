package tech.hdurmaz.mailservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.hdurmaz.mailservice.service.EmailService;

import javax.mail.MessagingException;
import java.io.IOException;

@RequestMapping("/mail")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final EmailService emailService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> sendMail(@RequestParam String toList,
                                           @RequestParam("file") MultipartFile multipartFile)
            throws IOException, MessagingException {
        log.info("Sending email to: " + toList);
        emailService.sendEmail(toList, multipartFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
