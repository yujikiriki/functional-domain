package co.s4n.comision

import co.s4n.comision.domain._

trait ComisionServices {
  import scala.util.Try

  val liquidar: Comision[Nueva] => Try[Comision[Liquidada]] = {
    c =>
      Try {
        c.copy( estado = Liquidada( ) )
      }
  }

  val anular: Comision[Liquidada] => Try[Comision[Anulada]] = {
    c =>
      Try {
        c.copy( estado = Anulada( ) )
      }
  }

  val aprobar: Comision[Liquidada] => Try[Comision[Aprobada]] = {
    c =>
      Try {
        c.copy( estado = Aprobada( ) )
      }
  }

  val facturar: Comision[Aprobada] => Try[Comision[Facturada]] = {
    c =>
      Try {
        c.copy( estado = Facturada( ) )
      }
  }

}

object ComisionServices extends ComisionServices
