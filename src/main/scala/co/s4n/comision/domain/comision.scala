package co.s4n.comision.domain

import scalaz._

case class Cliente( id: String, nombre: String )

sealed trait EstadoComision

final case class Nueva( ) extends EstadoComision

final case class Liquidada( ) extends EstadoComision

final case class Anulada( ) extends EstadoComision

final case class Aprobada( ) extends EstadoComision

final case class Facturada( ) extends EstadoComision

case class Comision[+Estado <: EstadoComision]( id: Option[Long],
                                               valorComision: Long,
                                               iva: Long,
                                               estado: Estado,
                                               cliente: Cliente )

object Comision {

  private def cambiarCliente[Estado <: EstadoComision](): Lens[Comision[EstadoComision], Cliente] = {
    Lens.lensu[Comision[EstadoComision], Cliente](
      set = ( comision, nuevoCliente ) => comision.copy( cliente = nuevoCliente ),
      get = ( comision ) => comision.cliente
    )
  }

  private def cambiarNombreCliente(): Lens[Cliente, String] = {
    Lens.lensu[Cliente, String](
      set = ( cliente, nuevoNombre ) => cliente.copy( nombre = nuevoNombre ),
      get = ( cliente ) => cliente.nombre
    )
  }

  def cambiarResponsable[Estado <: EstadoComision](): LensFamily[Comision[EstadoComision], Comision[EstadoComision], String, String] =
    cambiarCliente andThen cambiarNombreCliente

}