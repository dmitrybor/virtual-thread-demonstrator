package net.pydog.syncservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import net.pydog.syncservice.configuration.DelayServiceProperties;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class DelayServiceTest {

  @Mock
  private RestTemplate mockRestTemplate;

  @Mock
  private DelayServiceProperties mockDelayServiceProperties;

  @InjectMocks
  private DelayService delayService;

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 5, 15, 148})
  void testInvokesRemoteService(final Integer delayMs) {
    final String remoteServiceUrl = "http://remote.servie";

    when(mockDelayServiceProperties.getUrl()).thenReturn(remoteServiceUrl);
    when(mockRestTemplate.exchange(
        remoteServiceUrl + "/delay?ms=" + delayMs,
        HttpMethod.PUT,
        HttpEntity.EMPTY,
        Integer.class)
    ).thenReturn(new ResponseEntity<>(delayMs, HttpStatus.OK));

    Integer delayResult = delayService.delay(delayMs);

    assertEquals(delayMs, delayResult);
    verify(mockRestTemplate).exchange(
        remoteServiceUrl + "/delay?ms=" + delayMs,
        HttpMethod.PUT,
        HttpEntity.EMPTY,
        Integer.class
    );
  }

}
