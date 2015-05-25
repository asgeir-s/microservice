package com.cluda.microservice.service

import akka.http.scaladsl.model.StatusCodes._


class PingServiceSpec extends TestService {
  it should "responds accept the new stream and return the id" in {
    Get("/ping") ~> routes ~> check {
      status shouldBe OK
      val response = responseAs[String]
      assert(response.contains("pong"))
    }
  }

}
