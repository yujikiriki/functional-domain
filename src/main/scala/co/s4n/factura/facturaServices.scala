package co.s4n.factura

import java.math.RoundingMode
import org.joda.money.Money
import org.joda.time.DateTime
import scala.util.Try

sealed trait EstadoFactura
final case class Nueva( ) extends EstadoFactura
final case class Anulada( ) extends EstadoFactura
final case class Aprobada( ) extends EstadoFactura
final case class Causada( valor: Money ) extends EstadoFactura

case class Factura[State <: EstadoFactura]( fecha: DateTime, idCliente: String, valor: Money, iva: Double, state: State )

trait FacturaServices {
  
  val anular: Factura[Nueva] => Try[Factura[Anulada]] = {
    facturaNueva =>
      Try( facturaNueva.copy( state = new Anulada( ) ) )
  }

  val aprobar: Factura[Nueva] => Try[Factura[Aprobada]] = {
    facturaNueva =>
      Try( facturaNueva.copy( state = new Aprobada( ) ) )
  }

  val causar: Factura[Aprobada] => Try[Factura[Causada]] = {
    factura =>
      Try {
        val valor: Money = factura.valor.multipliedBy(
          1.0 + factura.iva,
          RoundingMode.CEILING
        )
        factura.copy( state = new Causada( valor ) )
      }
  }
}

object FacturaServices extends FacturaServices

