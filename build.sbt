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

ThisBuild / publishTo := {
  val nexus = nexusHost
  if (isSnapshot.value)
    Some("snapshots" at nexus + "/repository/maven-snapshots")
  else
    Some("releases" at nexus + "/repository/maven-releases")
}
