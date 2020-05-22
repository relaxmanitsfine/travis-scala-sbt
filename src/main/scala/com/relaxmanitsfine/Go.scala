package com.relaxmanitsfine

import java.io.PrintStream

object Go {
  def main(args: Array[String]): Unit =
    run(args.toList, System.out, System.err) match {
      case Left(returnCode) =>
        System.exit(returnCode)
      case Right(_) =>
        System.exit(0)
    }

  def run(args: List[String], stdout: PrintStream, stderr: PrintStream): Either[Int, Unit] = {
    if (args.length != 1) {
      printErrUsage(s"incorrect number of arguments: ${args.length.toString} != 1", separator, stderr)
      Left(1)
    } else {
      (for {
        arg <- args.headOption
        n <- parseStringAsInt(arg)
        i <- NonNegativeInt.fromInt(n)
      } yield {
        Fibonacci.takeN(i).mkString("\n")
      }).map(stdout.println) match {
        case Some(_) =>
          Right(())
        case None =>
          printErrUsage(s"invalid arguments: " + args.mkString(", "), separator, stderr)
          Left(1)
      }
    }
  }

  val separator = "\n\t"

  def printErrUsage(msg: String, separator: String, stderr: PrintStream): Unit =
    stderr.println(msg + separator + usage)


  def usage: String =
    "usage: Go <fibonacci_up_to>"

  def parseStringAsInt(s: String): Option[Int] =
    try {
      Some(s.toInt)
    } catch {
      case _: NumberFormatException =>
        None
    }

}
