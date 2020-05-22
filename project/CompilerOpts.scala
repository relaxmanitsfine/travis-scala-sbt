import sbt._

object CompilerOpts {
  lazy val compilerOpts =
    Seq(
      "-language:higherKinds"
    , "-encoding", "UTF-8"
    , "-deprecation"
    , "-unchecked"
    , "-feature"
    , "-Ywarn-value-discard"
    , "-Xlint"
    , "-Xfatal-warnings"
    , "-Ywarn-dead-code"
    , "-Xlint:inaccessible"
    , "-Xlint:adapted-args"
    , "-Xlint:nullary-override"
    , "-Ywarn-numeric-widen"
    , "-Wunused:imports"
    )

  lazy val consoleOpts =
    compilerOpts.filterNot(_ == "-Wunused:imports")
}
