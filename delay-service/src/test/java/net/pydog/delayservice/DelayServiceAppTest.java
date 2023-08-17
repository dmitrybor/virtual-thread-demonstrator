package net.pydog.delayservice;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class DelayServiceAppTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void contextLoads() {
  }

  @Test
  void testDelay() {
    webTestClient.put().uri("/delay?ms=10")
        .exchange()
        .expectStatus().isOk()
        .expectBody(Integer.class)
        .value(Matchers.is(10));
  }

}
