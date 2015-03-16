package ddd

trait Transformable[Entity, Record] {
  def toRecord( value: Entity ): Record
}

object Transformer {
  def toRecord[Entity, Record](entity: Entity)(implicit transformer: Transformable[Entity, Record]): Record = {
    transformer.toRecord( entity )
  }
}
