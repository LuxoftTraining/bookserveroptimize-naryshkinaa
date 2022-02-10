package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.util.Random

class BasicSimulation extends BaseParam{

  def scn() = scenario("Simple search") // A scenario is a chain of requests and pauses
    .repeat(numberRequests) {
      feed(feeder)
        .exec(http("find")
          .get("http://localhost:8080/books/find/simple/${text}"))
    }
}
