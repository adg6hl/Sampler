// set the name of the project
name := "Sampler"

organization := "gc"

version := "0.1.7"

scalaVersion := "2.9.0-1"

libraryDependencies ++= Seq(
	"junit" % "junit" % "4.8" % "test->default",
	"org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test->default",
	"org.mockito" % "mockito-all" % "1.8.5" %"test->default",
	"org.scala-tools.testing" % "scalacheck_2.9.0-1" % "1.9" % "test->default",
	"org.specs2" %% "specs2" % "1.5" % "test"
)

/** Compilation */
javacOptions ++= Seq("-Xmx1812m", "-Xms512m", "-Xss4m")

scalacOptions ++= Seq("-unchecked", "-deprecation")

maxErrors := 20

pollInterval := 1000
