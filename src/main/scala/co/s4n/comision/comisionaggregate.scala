package co.s4n.comision

import co.s4n.comision.domain._

class ComisionServices extends ComisionRepositoryModule {
  import scala.util.Try

  def crear(c: Comision[Nueva]): Comision[Nueva] = {
    val id = comisionRepository.add(c)
    c.copy(id = Some(id))
  }

  def liquidar(c: Comision[Nueva]): Try[Comision[Liquidada]] = {
    Try {
      c.copy(estado = Liquidada())
    }
  }

  def anular(c: Comision[Liquidada]): Try[Comision[Anulada]] = {
    Try {
      c.copy(estado = Anulada())
    }
  }

  def aprobar(c: Comision[Liquidada]): Try[Comision[Aprobada]] = {
    Try {
      c.copy(estado = Aprobada())
    }
  }

  def facturar(c: Comision[Aprobada]): Try[Comision[Facturada]] = {
    Try {
      c.copy(estado = Facturada())
    }
  }

}
