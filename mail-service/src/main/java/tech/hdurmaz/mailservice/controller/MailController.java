package tech.hdurmaz.mailservice.controller;

import java.io.IOException;
import javax.mail.MessagingException;
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
import tech.hdurmaz.clients.mail.SendFileMailAccountsRequest;
import tech.hdurmaz.mailservice.service.EmailService;

@RequestMapping("/mail")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MailController {

  private final EmailService emailService;

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<String> sendMail(@RequestParam("file") MultipartFile multipartFile,
      @RequestParam String toList)
      throws IOException, MessagingException {
    SendFileMailAccountsRequest request = new SendFileMailAccountsRequest(toList, multipartFile);
    log.info("Sending email to: " + toList);
    emailService.sendEmail(request);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
