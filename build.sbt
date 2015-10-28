import spray.revolver.RevolverPlugin._

seq(Revolver.settings: _*)

scalariformSettings

name := "functional-domain"

organization := "co.s4n"

version := "0.0.1"

scalaVersion := "2.11.7"

resolvers ++= Seq(
  "releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "com.chuusai"                 %%  "shapeless"           % "2.2.5"   withSources() withJavadoc(),
  "org.scalaz"                  %%  "scalaz-core"         % "7.1.4"   withSources() withJavadoc(),
  "com.softwaremill.macwire"    %%  "macros"              % "1.0.1"   withSources() withJavadoc(),
  "com.softwaremill.macwire"    %%  "runtime"             % "1.0.1"   withSources() withJavadoc(),
  "org.joda"                    %   "joda-money"          % "0.10.0"  withSources() withJavadoc(),
  "com.github.nscala-time"      %%  "nscala-time"         % "2.4.0"   withSources() withJavadoc(),
  "com.typesafe.scala-logging"  %%  "scala-logging"       % "3.1.0"   withSources() withJavadoc(),
  "org.scalatest"               %   "scalatest_2.11"      % "2.2.4"   % "test",
  "org.mockito"                 %   "mockito-core"        % "1.10.19" % "test"  withSources() withJavadoc(),
 "org.typelevel" %% "scalaz-contrib-210"        % "0.2",
  "org.typelevel" %% "scalaz-contrib-validation" % "0.2",
  "org.typelevel" %% "scalaz-contrib-undo"       % "0.2",
  // currently unavailable because there's no 2.11 build of Lift yet
  // "org.typelevel" %% "scalaz-lift"               % "0.2",
  "org.typelevel" %% "scalaz-nscala-time"        % "0.2",
  "org.typelevel" %% "scalaz-spire"              % "0.2"  
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
