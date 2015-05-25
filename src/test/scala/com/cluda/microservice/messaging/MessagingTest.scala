package com.cluda.microservice.messaging

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import org.scalatest._

import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * All unit tests should extend this.
 */
abstract class MessagingTest extends TestKit(ActorSystem("test", ConfigFactory.parseString( """
  akka.loggers = ["akka.testkit.TestEventListener"]
                                                                                             """))) with FlatSpecLike with ImplicitSender with Matchers with
OptionValues with Inside with Inspectors with BeforeAndAfterAll {

  implicit val timeout = Timeout(10 second)
  implicit val context = system.dispatcher
  
  def afterTest(): Unit ={}

  override protected def afterAll() {
    afterTest()
    TestKit.shutdownActorSystem(system)
    super.afterAll()
    system.shutdown()
  }

}


