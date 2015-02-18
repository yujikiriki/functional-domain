import spray.revolver.RevolverPlugin._

seq(Revolver.settings: _*)

name := "functional-domain"

organization := "co.s4n"

version := "0.0.1"

scalaVersion := "2.11.5"

resolvers ++= Seq(
  "releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "com.chuusai"                 %%  "shapeless"       % "2.1.0" withSources() withJavadoc(),
  "org.scalaz"                  %%  "scalaz-core"     % "7.1.1" withSources() withJavadoc(),
  "com.typesafe.scala-logging"  %%  "scala-logging"   % "3.1.0" withSources() withJavadoc(),
  "net.ceedubs"                 %%  "ficus"           % "1.1.1" withSources() withJavadoc(),
  "org.scalatest"               %%  "scalatest"       % "2.2.1" % "test"
)

scalacOptions ++= Seq(
  "-deprecation",           
  "-encoding", "UTF-8",
  "-feature",                
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",       
  "-Xlint",
  "-Yno-adapted-args",       
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",   
  "-Ywarn-value-discard",
  "-Xfuture"     
)
