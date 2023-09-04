package net.pydog.performance

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class DelayServicePerfTest extends Simulation {


  def BASE_URL: String = System.getProperty("baseUrl", "http://localhost")

  def DELAY_VALUE_MS: Int = System.getProperty("delayValueMs", "100").toInt

  def START_REQUEST_RATE: Int = System.getProperty("startRequestRate", "10").toInt

  def END_REQUEST_RATE: Int = System.getProperty("endRequestRate", "100").toInt

  def TEST_DURATION_SEC: Int = System.getProperty("durationSeconds", "60").toInt

  //Http configuration
  val httpProtocol: HttpProtocolBuilder = http.baseUrl(url = s"$BASE_URL")
    .acceptHeader(value = "application/json")

  //Scenario definition
  val scn: ScenarioBuilder = scenario(name = "Delay Service test")
    .exec(http(requestName = s"Delay $DELAY_VALUE_MS ms")
      .put("/delay")
      .queryParam("ms", DELAY_VALUE_MS)
      .check(status.is(expected = 200))
      .check(bodyString.is(expected = s"$DELAY_VALUE_MS"))
    )

  //Load scenario
  setUp(
    scn.inject(
      rampUsersPerSec(START_REQUEST_RATE).to(END_REQUEST_RATE).during(TEST_DURATION_SEC)
    )
  ).protocols(httpProtocol)

}
