package co.s4n.comision.domain

import org.scalatest.FunSuite

class ComisionTest extends FunSuite {

  val c: Comision[Nueva] = new Comision(
    id = None,
    valorComision = 1l,
    iva = 1l,
    Nueva( ),
    new Cliente( "CC1234567", "Pepito" )
  )

  test( "Comision lens para cambiar Cliente" ) {
    val cliente: String = Comision.cambiarResponsable.get( c )
    val comision: Comision[EstadoComision] = Comision.cambiarResponsable.set( c, "Paquito" )
    assert( "Paquito" === comision.cliente.nombre )
  }
}
