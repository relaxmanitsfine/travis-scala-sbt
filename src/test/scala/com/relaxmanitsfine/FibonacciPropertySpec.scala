package com.relaxmanitsfine

import org.scalacheck.{Gen, Prop}
import org.scalatest.prop._
import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.Checkers

@SuppressWarnings(Array("org.wartremover.warts.Any"))
class FibonacciPropertySpec extends AnyPropSpec with TableDrivenPropertyChecks with Matchers with Checkers {

  private val nTable =
    Table(
      ("n", "res")
    , (NonNegativeInt.fromIntUnsafe(0), 0)
    , (NonNegativeInt.fromIntUnsafe(1), 1)
    , (NonNegativeInt.fromIntUnsafe(2), 1)
    , (NonNegativeInt.fromIntUnsafe(3), 2)
    , (NonNegativeInt.fromIntUnsafe(4), 3)
    , (NonNegativeInt.fromIntUnsafe(5), 5)
    , (NonNegativeInt.fromIntUnsafe(10), 55)
    , (NonNegativeInt.fromIntUnsafe(20), 6765)
    , (NonNegativeInt.fromIntUnsafe(43), 433494437)
    )

  property("nth table driven examples") {
    forAll(nTable) { (n, res) =>
      Fibonacci.nth(n).shouldEqual(res)
    }
  }

  private val takeNTable =
    Table(
      ("n", "res")
    , (NonNegativeInt.fromIntUnsafe(0), Nil)
    , (NonNegativeInt.fromIntUnsafe(1), List(0))
    , (NonNegativeInt.fromIntUnsafe(2), List(0, 1))
    , (NonNegativeInt.fromIntUnsafe(3), List(0, 1, 1))
    , (NonNegativeInt.fromIntUnsafe(4), List(0, 1, 1, 2))
    , (NonNegativeInt.fromIntUnsafe(5), List(0, 1, 1, 2, 3))
    , (NonNegativeInt.fromIntUnsafe(6), List(0, 1, 1, 2, 3, 5))
    )

  property("takeN table driven examples") {
    forAll(takeNTable) { (n, res) =>
      Fibonacci.takeN(n).shouldEqual(res)
    }
  }

  property("takeN and nth agree") {
    val positive = Gen.choose(1, 1000).map(NonNegativeInt.fromIntUnsafe)
    check(Prop.forAll(positive) { n =>
      Fibonacci.takeN(n).lastOption.contains(Fibonacci.nth(NonNegativeInt.fromIntUnsafe(n.value - 1)))
    })
  }
}

