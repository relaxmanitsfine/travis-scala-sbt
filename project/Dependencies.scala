import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1"
  lazy val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.14.1"
  lazy val scalaTestCheck = "org.scalatestplus" %% "scalacheck-1-14" % "3.1.0.0"
  
}
