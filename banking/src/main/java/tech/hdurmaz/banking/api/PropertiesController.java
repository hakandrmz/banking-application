package tech.hdurmaz.banking.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.hdurmaz.banking.config.PropertiesConfigurer;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("environments")
@AllArgsConstructor
@RefreshScope
public class PropertiesController {

    private final PropertiesConfigurer configuration;

    @GetMapping
    public Map<String,String> getEnvironments(@RequestParam String key) {
        return configuration.getEnvironmentVariables(key);
    }

}
