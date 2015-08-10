organization := "com.cluda"

name := "microservice"

version := "0.1.0"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

test in assembly := {}

assemblyJarName in assembly := "microservice.jar"

assemblyOutputPath in assembly := file("docker/microservice.jar")

mainClass in assembly := Some("com.cluda.microservice.Boot")

resolvers += "sonatype-oss-snapshot" at "https://oss.sonatype.org/content/repositories/snapshots" // for xchange snapshots

libraryDependencies ++= {
  val akkaV       = "2.3.12"
  val akkaStreamV = "1.0"
  val scalaTestV  = "2.2.4"
  Seq(
    //"com.typesafe.akka"   %%    "akka-actor"                              %     akkaV,
    "com.typesafe.akka"     %%    "akka-stream-experimental"                %     akkaStreamV,
    "com.typesafe.akka"     %%    "akka-http-core-experimental"             %     akkaStreamV,
    "com.typesafe.akka"     %%    "akka-http-experimental"                  %     akkaStreamV,
    "com.typesafe.akka"     %%    "akka-http-spray-json-experimental"       %     akkaStreamV,
    "com.typesafe.akka"     %%    "akka-http-testkit-experimental"          %     akkaStreamV,
    "org.scalatest"         %%    "scalatest"                               %     scalaTestV      %     "test"

  )
}