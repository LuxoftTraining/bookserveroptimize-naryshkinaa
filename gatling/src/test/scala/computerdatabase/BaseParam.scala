package computerdatabase

import scala.util.Random
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.util.Random

trait BaseParam extends Simulation{
  val httpProtocol = http
    .baseUrl("http://computer-database.gatling.io") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val BOOKS_AMOUNT = 10_000

  def numberRequests = 300

  val random = new Random()

  val feeder = Iterator.continually {
    Map("text" -> randomText)
  }

  def randomText: String = {
    val title = "Book"+ random.nextInt(BOOKS_AMOUNT) +" "
    val authorName = "AuthorName"+random.nextInt(BOOKS_AMOUNT)+" "
    val authorSurname = "AuthorSurname"+random.nextInt(BOOKS_AMOUNT)
    title + authorName + authorSurname
  }

  def scn(): ScenarioBuilder

  setUp(
    scn().inject(rampUsers(1000).during(1)
    ).protocols(httpProtocol))
}