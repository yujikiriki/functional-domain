package co.s4n.comision.persistence

import co.s4n.comision.domain.{Cliente, Nueva, Comision}
import org.scalatest.FunSuite

class TransformableTest extends FunSuite {

  val c = new Comision(
    id = None,
    valorComision = 1l,
    iva = 1l,
    Nueva( ),
    new Cliente( "CC1234567", "Pepito" )
  )

  test( "Basic transformation Entity to Record test" ) {
    import TransformableDefaults._

    val record: (ComisionRecord, ClienteRecord) = Transformer.toRecord( c )
    assert( "CC1234567" === record._2.id )
  }
}
