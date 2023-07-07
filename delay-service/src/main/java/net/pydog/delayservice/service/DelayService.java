package net.pydog.delayservice.service;

import java.time.Duration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DelayService {

  public Mono<Integer> delay(Integer delayMs) {
    return Mono.just(delayMs).delayElement(Duration.ofMillis(delayMs));
  }

}
