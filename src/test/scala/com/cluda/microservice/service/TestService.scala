package com.cluda.microservice.service

import akka.actor.{ActorRef, Props}
import akka.event.Logging
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import akka.util.Timeout
import com.cluda.microservice.Service
import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration._


trait TestService extends FlatSpec with Matchers with ScalatestRouteTest with Service {
  override def testConfigSource = "akka.loglevel = DEBUG"

  override def config = ConfigFactory.load("application-test")

  override val logger = Logging(system, getClass)

  override implicit val timeout: Timeout  = Timeout(2.minutes)

  implicit val routeTestTimeout = RouteTestTimeout(20.second)
}
