package net.pydog.ptservice.web;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import net.pydog.ptservice.service.DelayService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SampleController.class)
class SampleControllerTest {

  @MockBean
  private DelayService mockDelayService;

  @Autowired
  private MockMvc mockMvc;

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 5, 10, 124})
  void testDelay(final int delayMs) throws Exception {
    when(mockDelayService.delay(delayMs)).thenReturn(delayMs);

    mockMvc.perform(put("/delay?ms=" + delayMs))
        .andExpect(status().isOk())
        .andExpect(content().string(String.valueOf(delayMs)));

    verify(mockDelayService).delay(delayMs);
  }
}
