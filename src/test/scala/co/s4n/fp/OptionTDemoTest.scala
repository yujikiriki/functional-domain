package co.s4n.fp

import org.scalatest.FunSuite
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

class OptionTDemoTest extends FunSuite {

  test( "OptionT test" ) {
    val result: Future[Option[(String, Int, Future[Int], Future[Int])]] = OptionTDemo.result
    val a: Option[(String, Int, Future[Int], Future[Int])] = Await.result( result, 1.seconds )
    a map {
      r: (String, Int, Future[Int], Future[Int]) =>
        val r3: Int = Await.result( r._3, 1.seconds )
        val r4: Int = Await.result( r._4, 1.seconds )
        assert( "a" === r._1 )
        assert( 1 === r._2 )
        assert( 1 === r3 )
        assert( 1 === r4 )
    }
    assert( true )
  }

}
