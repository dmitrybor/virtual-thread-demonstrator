package net.pydog.ptservice.service;

import net.pydog.ptservice.configuration.DelayServiceProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DelayService {

  private static final String DELAY_PATH = "/delay?ms=";
  private final DelayServiceProperties delayServiceProperties;
  private final RestTemplate restTemplate;

  public DelayService(DelayServiceProperties delayServiceProperties, RestTemplate restTemplate) {
    this.delayServiceProperties = delayServiceProperties;
    this.restTemplate = restTemplate;
  }

  public Integer delay(final Integer delayMs) {
    ResponseEntity<Integer> responseEntity = restTemplate.exchange(
        delayServiceProperties.getUrl() + DELAY_PATH + delayMs,
        HttpMethod.PUT,
        HttpEntity.EMPTY,
        Integer.class);
    return responseEntity.getBody();
  }

}
