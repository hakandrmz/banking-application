package tech.hdurmaz.mailservice.dto;

import lombok.Data;

@Data
public class MailDto {

  private String to;
  private String subject;
  private String text;
}
