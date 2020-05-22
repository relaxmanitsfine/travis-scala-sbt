package com.relaxmanitsfine

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GoSpec extends AnyFlatSpec with Matchers {
  "App run with no arguments" should "show error and usage" in {
    runMain(Nil).shouldEqual(
      (Left(1), StdOutErr("", "incorrect number of arguments: 0 != 1\n\tusage: Go <fibonacci_up_to>"))
    )
  }

  "App run with non-integer argument" should "show error and usage" in {
    runMain(List("burp")).shouldEqual(
      (Left(1), StdOutErr("", "invalid arguments: burp\n\tusage: Go <fibonacci_up_to>"))
    )
  }

  "App run with negative integer argument" should "show error and usage" in {
    runMain(List("-5")).shouldEqual(
      (Left(1), StdOutErr("", "invalid arguments: -5\n\tusage: Go <fibonacci_up_to>"))
    )
  }

  "App run with argument 10" should "return known good values" in {
    runMain(List("10")).shouldEqual(
      (Right(()), StdOutErr(Fibonacci.takeN(NonNegativeInt.fromIntUnsafe(10)).mkString("\n"), ""))
    )
  }

  def runMain(args: List[String]) : (Either[Int, Unit], StdOutErr) = {
    val os = new java.io.ByteArrayOutputStream()
    val out = new java.io.PrintStream(os)
    val es = new java.io.ByteArrayOutputStream()
    val err = new java.io.PrintStream(es)

    (Go.run(args, out, err), StdOutErr(
      os.toString("UTF-8").trim
    , es.toString("UTF-8").trim
    ))
  }
}

case class StdOutErr(stdout: String, stderr: String)
