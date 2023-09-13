package net.pydog.delayservice.web;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Duration;
import net.pydog.delayservice.service.DelayService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = HealthCheckController.class)
class HealthCheckControllerTest {

  @MockBean
  private DelayService delayService;

  @Autowired
  private WebTestClient webClient;

  @Test
  void testHealth() {
    Mockito.when(delayService.delay(10))
        .thenReturn(Mono.just(10).delayElement(Duration.ofMillis(10)));

    webClient.get()
        .uri("/health")
        .exchange()
        .expectStatus().isOk()
        .expectBody(Integer.class).value(Matchers.is(10));

    verify(delayService, times(1)).delay(10);
  }

}
