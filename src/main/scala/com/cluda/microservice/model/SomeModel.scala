package com.cluda.microservice.model

import spray.json.DefaultJsonProtocol

case class SomeModel(id: Option[Long], number1: Int, number2: Long, number3: BigDecimal, number4: BigDecimal, number5: BigDecimal)

object SomeModelJsonProtocol extends DefaultJsonProtocol {
  implicit val SomeModelFormat = jsonFormat6(SomeModel)
}