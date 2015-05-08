package co.s4n.fp

import co.s4n.factura.{Nueva, Factura}
import org.joda.money.{CurrencyUnit, Money}
import scalaz.Monoid
import scalaz.syntax.monoid._

case class CuentaContable( tipo: String, valor: Money )

trait CuentasContablesServices {

  def balanceDelTipo( tipo: String, cuentasDelDia: List[CuentaContable] )( implicit m: Monoid[Money] ): Money = {
    cuentasDelDia
      .filter( _.tipo == tipo )
      .foldLeft( mzero[Money] )( _ |+| _.valor )
  }

  def maximaCuentaDelTipo( tipo: String, cuentasDelDia: List[CuentaContable] )( implicit m: Monoid[Money] ): Money = {
    cuentasDelDia
      .filter( _.tipo == tipo )
      .foldLeft( mzero[Money] ) {
      ( acc, cuenta ) =>
        if ( cuenta.valor isGreaterThan acc )
          cuenta.valor
        else
          acc
    }
  }

  def balanceFacturas( facturas: List[Factura[Nueva]] )( implicit m: Monoid[Money] ): Money =
    facturas.foldLeft( mzero[Money] )( _ |+| _.valor )

}

object CuentasContablesServices extends CuentasContablesServices {

//  implicit object USDMonoid extends Monoid[Money] {
//    override def zero: Money = Money.zero( CurrencyUnit.USD )
//    override def append( f1: Money, f2: => Money ): Money = f1 plus f2
//  }

  def append( f1: Money, f2: => Money ): Money = f1 plus f2
  def zero( currencyUnit: CurrencyUnit ): Money = Money.zero( currencyUnit )
  implicit def MoneyMonoid( currencyUnit: CurrencyUnit ): Monoid[Money] = Monoid.instance[Money]( append, zero( currencyUnit ) )

}