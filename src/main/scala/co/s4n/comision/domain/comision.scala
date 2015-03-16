package co.s4n.comision.domain

sealed trait EstadoComision

final case class Nueva( ) extends EstadoComision

final case class Liquidada( ) extends EstadoComision

final case class Anulada( ) extends EstadoComision

final case class Aprobada( ) extends EstadoComision

final case class Facturada( ) extends EstadoComision

case class Comision[State <: EstadoComision](
                                              id: Option[Long],
                                              valorComision: Long,
                                              iva: Long,
                                              estado: State,
                                              cliente: Cliente
                                              )

case class Cliente( id: String, nombre: String )