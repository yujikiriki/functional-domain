package co.s4n.comision.persistence

import ddd._
import co.s4n.comision.domain.{EstadoComision, Comision}

case class ComisionRecord( id: Option[Long], valorComision: Long, iva: Long, estado: String, idCliente: String )

case class ClienteRecord( id: String, nombre: String )

class ComisionRepository extends Repository[Comision[EstadoComision]] {
  private val clienteDAO = new ClienteDAO()
  private val comisionDAO = new ComisionDAO()

  override def add( entity: Comision[EstadoComision] ): Long = {
    import TransformableDefaults._

    val r: (ComisionRecord, ClienteRecord) = Transformer.toRecord( entity )
    comisionDAO.insert( r._1 )
    clienteDAO.insert( r._2 )
  }

  override def get( id: Long ): Comision[EstadoComision] = ???

  override def remove( entity: Comision[EstadoComision] ): Long = ???

  override def list( ): List[Comision[EstadoComision]] = ???
}

class ClienteDAO extends DAO[ClienteRecord] {
  override def insert( record: ClienteRecord ): Long = ???

  override def update( record: ClienteRecord ): Long = ???

  override def delete( record: ClienteRecord ): Long = ???

  override def retrieve( ): List[ClienteRecord] = ???
}

class ComisionDAO extends DAO[ComisionRecord] {
  override def insert( record: ComisionRecord ): Long = ???

  override def update( record: ComisionRecord ): Long = ???

  override def delete( record: ComisionRecord ): Long = ???

  override def retrieve( ): List[ComisionRecord] = ???
}