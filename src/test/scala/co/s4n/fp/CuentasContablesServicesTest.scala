package co.s4n.fp

import org.joda.money.CurrencyUnit
import org.scalatest.FunSuite

import scalaz.Monoid

class CuentasContablesServicesTest extends FunSuite {
  import org.joda.money.Money

  implicit val r: Monoid[Money] = CuentasContablesServices.MoneyMonoid(CurrencyUnit.USD)

  private val cuentasContables = List(
    CuentaContable( "a", Money.parse( "USD 10" ) ),
    CuentaContable( "a", Money.parse( "USD 20" ) ),
    CuentaContable( "b", Money.parse( "USD 30" ) ),
    CuentaContable( "b", Money.parse( "USD 40" ) ),
    CuentaContable( "c", Money.parse( "USD 50" ) )
  )

  test( "Balance del día" ) {
    val balance: Money = CuentasContablesServices.balanceDelTipo( "a", cuentasContables )
    assert( Money.parse( "USD 30" ).isEqual( balance ) )
  }

  test( "Máxima del día" ) {
    val maxTipo: Money = CuentasContablesServices.maximaCuentaDelTipo( "b", cuentasContables )
    assert( Money.parse( "USD 40" ).isEqual( maxTipo ) )
  }
}
