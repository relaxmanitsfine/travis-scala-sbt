package com.relaxmanitsfine

sealed abstract case class NonNegativeInt(value: Int)

object NonNegativeInt {
  def fromInt(i: Int): Option[NonNegativeInt] =
    if (i >= 0)
      Some(new NonNegativeInt(i) {})
    else
      None

   // mostly for testing
  def fromIntUnsafe(i: Int): NonNegativeInt =
    fromInt(i).getOrElse(sys.error(s"value ${i.toString} is not a non-negative integer"))
}
