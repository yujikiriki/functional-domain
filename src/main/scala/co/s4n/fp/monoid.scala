package co.s4n.fp

/* Binary operations with:
 . Closed: given two elements of the same category, the operation delivers en element of the same category.
 . Identity: a (+) identity = identity (+) a = a
 . Associativity
 . Any type with an "add" notion and have an "identity" element could be a monoid:
    - Add operation: (A, A) => A
      Must be associative: (x, append(y, z)) == append(append(x, y), z )
    - An element zero of type A
*/

trait Monoide[A] {
  def append( f1: A, f2: => A ): A
  def zero: A
}

object BooleanMonoid {

  implicit val booleanAndMonoid: Monoide[Boolean] = new Monoide[Boolean] {
    override def append( f1: Boolean, f2: => Boolean ): Boolean =
      f1 && f2

    override def zero: Boolean = true
  }

  implicit val booleanOrMonoid: Monoide[Boolean] = new Monoide[Boolean] {
    override def append( f1: Boolean, f2: => Boolean ): Boolean =
      f1 || f2

    override def zero: Boolean = false
  }

  implicit val booleanXORMonoid: Monoide[Boolean] = new Monoide[Boolean] {
    override def append( f1: Boolean, f2: => Boolean ): Boolean =
      (f1 && !f2) || (!f1 && f2)

    override def zero: Boolean = false
  }

}


object SetMonoid {

  implicit def setUnionMonoid[T] = new Monoide[Set[T]] {
    override def append( f1: Set[T], f2: => Set[T] ): Set[T] =
      f1 union f2

    override def zero: Set[T] = Set.empty[T]
  }

}


object SuperAdder {
  import scalaz.Monoid
  import scalaz.syntax.monoid._

  def add[A]( items: List[A] )(implicit m: Monoid[A]): A =
    items.foldLeft( mzero[A] ) { _ |+| _ }

}