package net.pydog.performance

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class DelayServicePerfTest extends Simulation {

  //Http configuration
  val httpProtocol = http.baseUrl(url = "http://localhost:8000")
    .acceptHeader(value = "application/json")

  //Scenario definition
  val scn = scenario(name = "Delay Service test")
    .exec(http(requestName = "Delay 900 ms")
      .put("/delay")
      .queryParam("ms", 900)
      .check(status.is(expected = 200))
      .check(bodyString.is(expected = "900"))
    )

  //Load scenario
  setUp(
    scn.inject(
      constantConcurrentUsers(20).during(5.minutes)
    )
  ).protocols(httpProtocol)

}
