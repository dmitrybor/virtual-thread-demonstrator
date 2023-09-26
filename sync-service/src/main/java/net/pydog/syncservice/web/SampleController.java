package net.pydog.syncservice.web;

import net.pydog.syncservice.service.DelayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  private final DelayService delayService;

  public SampleController(DelayService delayService) {
    this.delayService = delayService;
  }

  @PutMapping("/delay")
  public ResponseEntity<Integer> delay(@RequestParam("ms") Integer ms) {
    Integer result = delayService.delay(ms);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/threadInfo")
  public String test() {
    return Thread.currentThread().toString();
  }

}
