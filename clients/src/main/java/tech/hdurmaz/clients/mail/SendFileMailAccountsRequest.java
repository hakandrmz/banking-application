package tech.hdurmaz.clients.mail;

import org.springframework.web.multipart.MultipartFile;

public record SendFileMailAccountsRequest(
    String toList,
    MultipartFile multipartFile
) {

}
