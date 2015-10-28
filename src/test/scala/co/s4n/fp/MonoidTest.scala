package co.s4n.fp

import org.scalatest.FunSuite

class MonoidTest extends FunSuite {

  test("Smoke test") {
    val str = "a" ++ "b" // Sequence concatenation
    assert("ab" === str)
  }

  test("Boolean AND monoid") {
    import BooleanMonoid._
    assert(booleanAndMonoid.append(true, true))
  }

  test("Boolean OR monoid") {
    import BooleanMonoid._
    assert(booleanOrMonoid.append(false, true))
  }

  test("Boolean XOR monoid") {
    import BooleanMonoid._
    assert(booleanXORMonoid.append(true, false))
  }

  test("Set of Int monoid") {
    import SetMonoid._
    val s1 = Set(1)
    val s2 = Set(2)
    assert((Set(1, 2)) == setUnionMonoid.append(s1, s2))
  }

  test("Monoid List of Int add") {
    import scalaz.std.anyVal._

    assert(6 === SuperAdder.add(List(1, 2, 3)))
  }

  //  test( "Monoid List of Option[Int] add" ) {
  //    assert( 6 === SuperAdder.add( List( Some( 1 ), Some( 2 ), None, Some( 3 ) ) ) )
  //  }

}
