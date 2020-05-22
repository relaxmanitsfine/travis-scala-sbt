package com.relaxmanitsfine

import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.Checkers

@SuppressWarnings(Array("org.wartremover.warts.Any"))
class NonNegativeIntSpec extends AnyPropSpec with Matchers with Checkers {

  property("safe constructor only accepts non-negative values") {
    check((i: Int) =>
      if(i < 0)
        NonNegativeInt.fromInt(i).isEmpty
      else
        NonNegativeInt.fromInt(i).isDefined
    )
  }

  property("unsafe constructor behaves as expected") {
    check((i: Int) =>
      if(i < 0) {
        intercept[RuntimeException] {
          NonNegativeInt.fromIntUnsafe(i)
        }.getMessage == s"value ${i.toString} is not a non-negative integer"
      } else {
        NonNegativeInt.fromIntUnsafe(i) == NonNegativeInt.fromIntUnsafe(i)
      }
    )
  }


}
