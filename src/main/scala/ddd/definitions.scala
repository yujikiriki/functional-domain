package ddd

trait Repository[T] {
  def add( entity: T ): Long
  def remove( entity: T ): Long
  def list(): List[T]
  def get( id: Long ): T
}

trait DAO[T] {
  def insert( record: T ): Long
  def delete( record: T ): Long
  def update( record: T ): Long
  def retrieve(): List[T]
}
