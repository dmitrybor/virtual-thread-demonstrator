package net.pydog.asyncservice.web;

import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SampleController {

  @GetMapping("/test")
  public Mono<String> test() {
    return Mono.just("test").delayElement(Duration.ofMillis(200));
  }
}
