package co.s4n.comision

import scala.util.Try


sealed trait EstadoComision
case class Liquidada() extends EstadoComision
case class Anulada() extends EstadoComision
case class Aprobada() extends EstadoComision
case class Facturada() extends EstadoComision

sealed trait TipoDocumento
case object CedulaCiudadania extends TipoDocumento

case class Comision(
                     id: Option[Long],
                     tipDocumentoDeudor: TipoDocumento,
                     valorComision: Long,
                     iva: Long,
                     total: Long,
                     estado: EstadoComision
                     )

class ComisionServices[ State <: EstadoComision ] {

  def liquidar( c: Comision ): Try[Comision] = Try {
    c.copy( estado = Liquidada() )
  }

  def anular[ T >: State <: Liquidada ]( c: Comision ): Try[Comision] = Try {
    c.copy( estado = Anulada() )
  }

  def aprobar[ T >: State <: Liquidada ]( c: Comision ): Try[Comision] = Try {
    c.copy( estado = Aprobada() )
  }

  def facturar[ T >: State <: Aprobada ]( c: Comision ): Try[Comision] = Try {
    c.copy( estado = Facturada() )
  }
}

object ComisionServices {
  def create(): ComisionServices[Liquidada] = new ComisionServices[Liquidada]
}