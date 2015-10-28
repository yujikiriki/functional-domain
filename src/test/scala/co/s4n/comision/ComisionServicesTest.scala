package co.s4n.comision

import co.s4n.comision.infrastructure.{ ClienteRecord, ComisionRecord, ClienteDAO, ComisionDAO }
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import co.s4n.comision.domain._

import scala.util.{ Success, Failure, Try }

class ComisionServicesTest extends FunSuite with MockitoSugar {

  val c: Comision[Nueva] = new Comision(
    id = None,
    valorComision = 1l,
    iva = 1l,
    Nueva(),
    new Cliente("CC1234567", "Pepito")
  )

  lazy val comisionRepository = mock[ComisionRepository]
  when(comisionRepository.add(c)).thenReturn(7l)

  val services = new ComisionServices()

  test("Create a Comision using DI") {
    val creada: Comision[Nueva] = services.crear(c)
    info(s"$creada")
    assert(creada.id === Some(7l))
  }

  ignore("Typing example") {
    // val facturar = ComisionServices.facturar( c ) // <= No compila.
    assert(true)
  }

  test("Functional composition: flatMap") {
    val comisionProcesada: Try[Comision[Facturada]] =
      services.liquidar(c)
        .flatMap(services.aprobar(_)
          .flatMap(services.facturar(_)))

    comisionProcesada match {
      case Success(procesada) => assert(procesada.estado == Facturada())
      case Failure(ex) => fail(ex)
    }
  }

  test("Functional composition: for-comprehension") {
    val comisionProcesada: Try[Comision[Facturada]] = for {
      liquidada <- services.liquidar(c)
      aprobada <- services.aprobar(liquidada)
      facturada <- services.facturar(aprobada)
    } yield facturada

    comisionProcesada match {
      case Success(procesada) => assert(procesada.estado == Facturada())
      case Failure(ex) => fail(ex)
    }
  }

}
