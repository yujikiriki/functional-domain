package co.s4n.comision.domain

import co.s4n.comision.infrastructure._
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._

class ComisionRepositoryTest extends FunSuite with MockitoSugar {

  val c: Comision[Nueva] = new Comision(
    id = None,
    valorComision = 1l,
    iva = 1l,
    Nueva( ),
    new Cliente( "", "" )
  )

  test( "Mocked DI test" ) {
    val comisionDAO = mock[ComisionDAO]
    when( comisionDAO.insert( ComisionRecord( None, 1l, 1l, "", "" ) ) ).thenReturn( 2l )

    val clienteDAO = mock[ClienteDAO]
    when( clienteDAO.insert( ClienteRecord( "", "" ) ) ).thenReturn( 2l )

    val repo = new ComisionRepository( comisionDAO, clienteDAO )
    val res: Long = repo.add( c )
    info( s"res = $res" )
    assert( 2L === res )
  }

}
