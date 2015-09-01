package com.cluda.microservice

import akka.actor.{Actor, ActorLogging}
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._

class PongActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case "pong" =>
      sender() ! HttpResponse(OK, entity = "ping")
  }
}
