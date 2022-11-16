package tech.hdurmaz.banking.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@org.springframework.context.annotation.PropertySource("application.yml")
@RequiredArgsConstructor
public class PropertiesConfigurer {

    private final Environment environment;

    public Map<String,String> getEnvironmentVariables(String key) {
        HashMap<String, String> env = new HashMap<>();
        env.put(key,environment.getProperty(key));
        return env;
    }
}
