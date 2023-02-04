package tech.hdurmaz.mailservice.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@ConfigurationProperties(prefix = "spring")
@Data
@Slf4j
@Configuration
public class MailProperties {

    private Map<String, String> mail;
}
