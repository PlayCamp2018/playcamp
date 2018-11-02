name := """PlayCamp2018"""
organization := "de.htw-berlin"
maintainer := "Fabian Ket <s0549108@htw-berlin.de>"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.6"

libraryDependencies += guice


packageSummary := "PlayCamp2018 Debian Package"
packageDescription := """PlayCamp 2018 a Project Communication Platform"""

debianPackageDependencies := Seq("java8-runtime-headless")
enablePlugins(DebianPlugin)

libraryDependencies ++= Seq(
  "uk.co.panaxiom" %% "play-jongo" % "2.1.0-jongo1.3"
)