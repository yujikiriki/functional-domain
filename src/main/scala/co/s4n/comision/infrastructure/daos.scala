package co.s4n.comision.infrastructure

import ddd.DAO

case class ComisionRecord( id: Option[Long], valorComision: Long, iva: Long, estado: String, idCliente: String )

case class ClienteRecord( id: String, nombre: String )

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