package tech.hdurmaz.mailservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.hdurmaz.mailservice.dto.MailDto;
import tech.hdurmaz.mailservice.service.EmailService;

@RequestMapping("/mail")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MailController {

  private final EmailService emailService;

  @PostMapping
  public ResponseEntity<String> sendMail(@RequestBody MailDto mailDto) {

    log.error("An ERROR Message");

    return new ResponseEntity<>(emailService.sendSimpleMail(mailDto), HttpStatus.OK);
  }

}
