package co.s4n.comision.persistence

import co.s4n.comision.domain.{EstadoComision, Comision}

trait Transformable[Entity, Record] {
  def toRecord( value: Entity ): Record
}

object Transformer {
  def toRecord[Entity, Record](entity: Entity)(implicit transformer: Transformable[Entity, Record]): Record = {
    transformer.toRecord( entity )
  }
}

object TransformableDefaults {

  implicit def comisionTransformable[State <: EstadoComision] = new Transformable[Comision[State], (ComisionRecord, ClienteRecord)] {
    def toRecord( entity: Comision[State] ): (ComisionRecord, ClienteRecord) = {
      val comisionRecord = new ComisionRecord(
        id = entity.id,
        valorComision = entity.valorComision,
        iva = entity.iva,
        estado = entity.estado.toString,
        idCliente = entity.cliente.id
      )
      val clienteRecord = new ClienteRecord(
        id = entity.cliente.id,
        nombre = entity.cliente.nombre
      )
      (comisionRecord, clienteRecord)
    }
  }

}

