package co.s4n.factura

import org.joda.money.Money
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.util.Try

class FacturaServicesTest extends FunSuite {

  val f = new Factura[Nueva](
    fecha = DateTime.now(),
    idCliente = "",
    valor = Money.parse("COP 12000"),
    iva = 0.16d,
    Nueva()
  )

  test("Causar factura") {
    import FacturaServices._
    val result: Try[Factura[Causada]] = for {
      aprobada <- aprobar(f)
      causada <- causar(aprobada)
    } yield causada

    result.map {
      c =>
        val reference = math.BigDecimal(13920).bigDecimal
        val divide = reference.divide(c.state.valor.getAmount)
        assert(1 === divide)
    } recover {
      case _ => fail()
    }
    ()
  }

}
