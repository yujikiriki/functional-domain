package co.s4n.comision.domain

import co.s4n.comision.infrastructure._
import ddd._

object ComisionRepository {

  implicit def comisionTransformable[State <: EstadoComision] = new Transformable[Comision[State], (ComisionRecord, ClienteRecord)] {
    def toRecord(entity: Comision[State]): (ComisionRecord, ClienteRecord) = {

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

class ComisionRepository(comisionDAO: ComisionDAO, clienteDAO: ClienteDAO) extends Repository[Comision[EstadoComision]] {
  import co.s4n.comision.domain.ComisionRepository._

  override def add(entity: Comision[EstadoComision]): Long = {
    val r: (ComisionRecord, ClienteRecord) = Transformer.toRecord(entity)
    comisionDAO.insert(r._1)
    clienteDAO.insert(r._2)
  }

  override def get(id: Long): Comision[EstadoComision] = ???

  override def remove(entity: Comision[EstadoComision]): Long = ???

  override def list(): List[Comision[EstadoComision]] = ???
}

trait ComisionRepositoryModule {
  import com.softwaremill.macwire._

  lazy val comisionDAO = wire[ComisionDAO]
  lazy val clienteDAO = wire[ClienteDAO]
  lazy val comisionRepository = wire[ComisionRepository]
}
