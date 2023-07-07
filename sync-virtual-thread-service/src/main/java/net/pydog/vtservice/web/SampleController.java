package net.pydog.vtservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  @GetMapping("/test")
  public String test() throws InterruptedException {
    Thread.sleep(200);
    return Thread.currentThread().toString();
  }
}
