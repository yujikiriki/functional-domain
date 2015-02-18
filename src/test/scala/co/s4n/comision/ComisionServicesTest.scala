package co.s4n.comision

import org.scalatest.FunSuite

import scala.util.Try

class ComisionServicesTest extends FunSuite {

  test( "State test" ) {
    val c = Comision( None, CedulaCiudadania, 1000l, 16l, 1000l, Liquidada() )
    val service = ComisionServices.create()
    val facturar: Try[Comision] = service.facturar( c ) // <= No compila.
    assert( true )
  }
}
