package co.s4n.factura

import co.s4n.factura.TicketsService.Requestable
import org.scalatest.FunSuite

import scalaz._
import Scalaz._

class TicketsServiceTest extends FunSuite {
  import TicketsService._

  test("flatMap without providing an interpreter, A.K.A. Look mom, no hands!") {
    val buy: Requestable[List[Ticket]] =
      TicketsService.buy(5).flatMap(discounts)
    assert(true)
  }

  test("Free Program") {
    val program: Requestable[List[Ticket]] = for {
      ts <- buy(5)
      dts <- discounts(ts)
      vdts <- vip(dts)
    } yield (vdts)
    assert(true)
  }

  test("Filter that shit") {
    val tks: List[Ticket] = List(Ticket("", ""))
    val requestable = filterM[Ticket, Requestable](tks)(isBonus)
    assert(true)
  }

  test("Sequence that shit") {
    val tks = List(Ticket("", ""))
    val requests: List[Requestable[List[Ticket]]] =
      List(buy(5), discounts(tks))
    val seq: Requestable[List[List[Ticket]]] = requests.sequence
    assert(true)
  }

  test("Interpreter") {
    import co.s4n.factura.OptionInterpreter
    import co.s4n.factura.ListInterpreter

    val program: Requestable[List[Ticket]] = for {
      ts <- buy(5)
      dts <- discounts(ts)
      vdts <- vip(dts)
    } yield (vdts)

    val res = Free.runFC(program)(OptionInterpreter)
    info(s"res = $res")

    val resF = Free.runFC(program)(ListInterpreter)
    info(s"resF = $resF")
  }
}
