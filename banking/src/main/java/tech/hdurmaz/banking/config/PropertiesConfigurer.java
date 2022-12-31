package tech.hdurmaz.banking.config;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

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
