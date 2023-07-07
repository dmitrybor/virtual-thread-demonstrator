package net.pydog.delayservice.web;


import net.pydog.delayservice.service.DelayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SampleController {

  private final DelayService delayService;

  public SampleController(DelayService delayService) {
    this.delayService = delayService;
  }

  @PutMapping("/delay")
  public ResponseEntity<Mono<Integer>> delay(@RequestParam("ms") Integer ms) {
    return new ResponseEntity<>(delayService.delay(ms), HttpStatus.OK);
  }

}
