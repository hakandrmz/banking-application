package tech.hdurmaz.banking.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class PropertiesConfigurer {

    private final Environment environment;

    public Map<String, String> getEnvironmentVariables(String key) {
        HashMap<String, String> env = new HashMap<>();
        env.put(key, environment.getProperty(key));
        return env;
    }
}
