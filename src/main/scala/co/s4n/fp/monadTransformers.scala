package co.s4n.fp

import scalaz._
import scalaz.std.AllInstances._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object OptionTDemo {

  def f1: Future[Option[String]] = Future( Some( "a" ) )
  def f2: Option[Int] = Some( 1 )
  def f3: Future[Int] = Future( 1 )
  def f4: Option[Future[Int]] = Some( Future( 1 ) )

  // final case class OptionT[F[_], A](run: F[Option[A]]) {
  val result: Future[Option[(String, Int, Future[Int], Future[Int])]] = ( for {
    a <- OptionT[Future,String]( f1 )
    b <- OptionT[Future, Int]( Future.successful( f2 ) )
    // Las que siguen son bellas galadas
    c <- OptionT[Future,Future[Int]]( Future.successful( Some( f3 ) ) )
    d <- OptionT[Future, Future[Int]]( Future.successful( f4 ) )
  } yield ( a, b, c, d ) ).run

}
