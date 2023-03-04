package tech.hdurmaz.mailservice.service;

import tech.hdurmaz.clients.mail.CustomerRegistrationMail;
import tech.hdurmaz.clients.mail.SendFileMailAccountsRequest;

public interface EmailService {

  void sendEmail(SendFileMailAccountsRequest request);

  void sendRegistrationEmail(CustomerRegistrationMail request);
}
