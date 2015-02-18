package co.s4n.comision

import scala.util.Try

sealed trait EstadoComision

case class Nueva( ) extends EstadoComision

case class Liquidada( ) extends EstadoComision

case class Anulada( ) extends EstadoComision

case class Aprobada( ) extends EstadoComision

case class Facturada( ) extends EstadoComision

case class Comision[State <: EstadoComision]( id: Option[Long], valorComision: Long, iva: Long, estado: State )

trait ComisionServices {

  val liquidar: Comision[Nueva] => Try[Comision[Liquidada]] = {
    c =>
      Try {
        c.copy( estado = Liquidada( ) )
      }
  }

  val anular: Comision[Liquidada] => Try[Comision[Anulada]] = {
    c =>
      Try {
        c.copy( estado = Anulada( ) )
      }
  }

  val aprobar: Comision[Liquidada] => Try[Comision[Aprobada]] = {
    c =>
      Try {
        c.copy( estado = Aprobada( ) )
      }
  }

  val facturar: Comision[Aprobada] => Try[Comision[Facturada]] = {
    c =>
      Try {
        c.copy( estado = Facturada( ) )
      }
  }
}

object ComisionServices extends ComisionServices