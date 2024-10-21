package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetProductsSimulation extends Simulation{

  val httpConf = http.baseUrl(url = "https://dummyjson.com/")
    .header(name = "Accept", value = "application/json")
    .header(name = "content-type", value = "application/json")

  val scn = scenario(scenarioName = "Get Products")
    .exec(http(requestName = "get products")
      .get(url = "/products")
      .header(name = "Accept", value = "application/json")
      .check(status is 200))

    .pause(duration = 3)

    .exec(http(requestName = "get user request")
      .get("/products/2")
      .check(status is 200))

    .pause(duration = 2)

    .exec(http(requestName = "get all user request")
      .get("/products/search?q=iphone")
      .check(status is 200))

  setUp(scn.inject(atOnceUsers(users = 1))).protocols(httpConf)
}
