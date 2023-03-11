package tech.hdurmaz.apigw.security;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class ApiKeyAuthorizationFilter implements GlobalFilter, Ordered {

  private static final String API_KEY = "ApiKey";
  private final FakeApiAuthorizationChecker fakeApiAuthorizationChecker;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    log.info("ApiKeyAuthorizationFilter... checking the key");

    Route attribute = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    String application = attribute.getId();

    List<String> apiKey = exchange.getRequest().getHeaders().get(API_KEY);
    if (Objects.nonNull(apiKey)) {
      log.info("Got apiKey from request. :" + apiKey);
    }

    if (application == null
        || Objects.requireNonNull(apiKey).isEmpty()
        || !fakeApiAuthorizationChecker.isAuthorized(apiKey.get(0), application)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized.");
    }

    log.info("ApiKey: " + apiKey.get(0));
    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
