organization := "com.cluda"

name := "microservice"

version := "0.2.0"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

test in assembly := {}

assemblyJarName in assembly := "microservice.jar"

assemblyOutputPath in assembly := file("docker/microservice.jar")

mainClass in assembly := Some("com.cluda.microservice.Boot")

libraryDependencies ++= {
  val akkaV       = "2.4.2"
  val scalaTestV  = "2.2.6"
  Seq(
    "com.typesafe.akka"     %%    "akka-actor"                              %     akkaV,
    "com.typesafe.akka"     %%    "akka-stream"                             %     akkaV,
    "com.typesafe.akka"     %%    "akka-http-core"                          %     akkaV,
    "com.typesafe.akka"     %%    "akka-http-experimental"                  %     akkaV,
    "com.typesafe.akka"     %%    "akka-http-testkit"                       %     akkaV,
    "com.typesafe.akka"     %%    "akka-http-spray-json-experimental"       %     akkaV,
    "org.scalatest"         %%    "scalatest"                               %     scalaTestV      %     "test"
  )
}