package com.cluda.microservice

import java.util.UUID

import akka.actor.{ActorSystem, Props}
import akka.event.LoggingAdapter
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.stream.Materializer
import akka.util.Timeout
import com.typesafe.config.Config

import scala.concurrent.{ExecutionContextExecutor, Future}

trait Service {
  implicit val system: ActorSystem

  implicit def executor: ExecutionContextExecutor

  implicit val materializer: Materializer
  implicit val timeout: Timeout

  def config: Config

  val logger: LoggingAdapter
  val runID = UUID.randomUUID()


  /**
   * Start a actor and pass it the decodedHttpRequest.
   * Returns a future. If anything fails it returns HttpResponse with "BadRequest",
   * else it returns the HttpResponse returned by the started actor
   *
   * @param props of the actor to start
   * @return Future[HttpResponse]
   */
  def perRequestActor[T](props: Props, message: T): Future[HttpResponse] = {
    (system.actorOf(props) ? message)
      .recover { case _ => HttpResponse(InternalServerError, entity = "InternalServerError") }
      .asInstanceOf[Future[HttpResponse]]
  }

  val routes = {
    pathPrefix("ping") {
      complete {
        HttpResponse(OK, entity = "runID: " + runID)
      }
    } ~
      pathPrefix("route" / Segment) { someString =>
        pathPrefix("deep") {
          post {
            entity(as[String]) { entityString =>
              complete {
                "this is the POST route: route/" + someString + "/deep. With entity: " + entityString
              }
            }
          } ~
            get {
              complete {
                "this is the GET route: route/" + someString + "/deep."
              }
            }
        }
      } ~
      pathPrefix("pong") {
        complete {
          perRequestActor(Props[PongActor], "pong")
        }
      }


  }
}
