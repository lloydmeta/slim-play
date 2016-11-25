name := """slim-play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  ws % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0-M1" % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"