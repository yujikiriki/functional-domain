package co.s4n.comision

import org.scalatest.FunSuite

import scala.util.{Failure, Success, Try}

class ComisionServicesTest extends FunSuite {

  val c = Comision( id = None, valorComision = 1l, iva = 1l, Nueva( ) )

  test( "Typing example" ) {
    //val facturar = ComisionServices.facturar( c ) // <= No compila.
    assert( true )
  }

  test( "Functional composition" ) {
    import ComisionServices._

    val comisionProcesada: Try[Comision[Facturada]] = for {
      liquidada <- liquidar( c )
      aprobada <- aprobar( liquidada )
      facturada <- facturar( aprobada )
    } yield facturada

    comisionProcesada match {
      case Success( procesada ) => assert( procesada.estado == Facturada( ) )
      case Failure( ex ) => fail( ex )
    }
  }
}
