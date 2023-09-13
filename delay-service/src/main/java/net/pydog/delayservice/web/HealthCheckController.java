package net.pydog.delayservice.web;

import net.pydog.delayservice.service.DelayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HealthCheckController {
  private final DelayService delayService;

  public HealthCheckController(DelayService delayService) {
    this.delayService = delayService;
  }

  @GetMapping("/health")
  public ResponseEntity<Mono<Integer>> health() {
    return new ResponseEntity<>(delayService.delay(10), HttpStatus.OK);
  }

}
