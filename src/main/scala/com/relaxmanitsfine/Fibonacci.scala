package com.relaxmanitsfine

import scala.annotation.tailrec

object Fibonacci {
  def nth(n: NonNegativeInt): BigInt = {
    @tailrec
    def go(x: Int, prev: BigInt, current: BigInt): BigInt =
      x match {
        case 0 =>
          prev
        case 1 =>
          current
        case _ =>
          go(x - 1, current, current + prev)
      }
    go(n.value, 0, 1)
  }

  def takeN(n: NonNegativeInt): List[BigInt] = {
    /*
     * Yes, I know the canonical example in scala is a Stream. I wanted
     * to do a tail recursive example instead.
     */
    @tailrec
    def go(x: Int, prev: BigInt, current: BigInt, acc: List[BigInt]): List[BigInt] =
      x match {
        case 1 =>
          acc
        case _ =>
          go(x - 1, current, current + prev, acc :+ (current + prev))
      }

    n match {
      case NonNegativeInt(0) =>
        List[BigInt]()
      case NonNegativeInt(1) =>
        List[BigInt](0)
      case NonNegativeInt(v) =>
        go(v - 1, 0, 1, List(0, 1))
    }

  }

}
