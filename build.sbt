import Dependencies._
import CompilerOpts._
import Nexus._

ThisBuild / scalaVersion := "2.13.2"
ThisBuild / organization := "com.relaxmanitsfine"

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    Defaults.itSettings,
    name := "travis-scala-sbt",
    libraryDependencies ++= Seq(
      scalaTest % "it,test"
    , scalaCheck % "it,test"
    , scalaTestCheck % "it,test"
    ),
    assembly / mainClass := Some("com.relaxmanitsfine.Go"),
    assembly / assemblyJarName := {
      s"${name.value}_${scalaBinaryVersion.value}-${version.value}.jar"
    },
    assembly / test := {},
    wartremoverErrors ++= Warts.unsafe,
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oD")
  )

ThisBuild / scalacOptions ++= compilerOpts
Compile / console / scalacOptions ++= consoleOpts
Test / console / scalacOptions ++= consoleOpts

ThisBuild / coverageEnabled := true

ThisBuild / bintrayReleaseOnPublish := false
ThisBuild / licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
ThisBuild / homepage := Some(url("https://github.com/relaxmanitsfine/travis-scala-sbt"))
ThisBuild / releaseEarlyEnableSyncToMaven := false
ThisBuild / developers := List(
  Developer(
    "relaxmanitsfine", "RelaxManItsFine", "relaxmanitsfine@gmail.com"
   , url = url("https://github.com/relaxmanitsfine")
))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/relaxmanitsfine/travis-scala-sbt")
  , "scm:git:git@github.com:relaxmanitsfine/travis-scala-sbt.git")
)
Global / pgpPublicRing := file("./keys/pubring.asc")
Global / pgpSecretRing := file("./keys/secring.asc")
Global / releaseEarlyWith := BintrayPublisher
Global / publishMavenStyle := false
Global / releaseEarlyEnableLocalReleases := true

