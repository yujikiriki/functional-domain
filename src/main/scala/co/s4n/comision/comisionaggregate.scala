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

  def liquidar( c: Comision[Nueva] ): Try[Comision[Liquidada]] = Try {
    c.copy( estado = Liquidada( ) )
  }

  def anular( c: Comision[Liquidada] ): Try[Comision[Anulada]] = Try {
    c.copy( estado = Anulada( ) )
  }

  def aprobar( c: Comision[Liquidada] ): Try[Comision[Aprobada]] = Try {
    c.copy( estado = Aprobada( ) )
  }

  def facturar( c: Comision[Aprobada] ): Try[Comision[Facturada]] = Try {
    c.copy( estado = Facturada( ) )
  }
}

object ComisionServices extends ComisionServices