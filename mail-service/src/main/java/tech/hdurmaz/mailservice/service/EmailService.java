package tech.hdurmaz.mailservice.service;

import tech.hdurmaz.mailservice.dto.MailDto;

public interface EmailService {

  String sendSimpleMail(MailDto mailDto);
}
