ThisBuild / useCoursier := true

ThisBuild / scalaVersion     := "3.1.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.rover"
ThisBuild / organizationName := "rover"

lazy val root = (project in file("."))
  .settings(
    scalaVersion := "3.1.0",
    name := "rover",
    libraryDependencies ++= Dependencies.Cats.deps ++ Dependencies.Test.deps
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
