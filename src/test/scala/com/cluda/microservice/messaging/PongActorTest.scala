package com.cluda.microservice.messaging

import akka.actor.Props
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.testkit.TestActorRef
import com.cluda.microservice.PongActor

class PongActorTest extends MessagingTest {

  "When the PongActorTest received a 'ping' it" should
    "responds with a HttpRequest with status code OK and a entity with 'pong'" in {
    val actor = TestActorRef(Props[PongActor])
    actor ! "ping"
    val responds = expectMsgType[HttpResponse]
    assert(responds == HttpResponse(OK, entity = "pong"))
  }

}
