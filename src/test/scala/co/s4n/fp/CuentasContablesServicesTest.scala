package co.s4n.fp

import co.s4n.factura.{ Nueva, Factura }
import org.joda.money.CurrencyUnit
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scalaz.Monoid

class CuentasContablesServicesTest extends FunSuite {
  import org.joda.money.Money

  private val cuentasContables = List(
    CuentaContable("a", Money.parse("USD 10")),
    CuentaContable("a", Money.parse("USD 20")),
    CuentaContable("b", Money.parse("USD 30")),
    CuentaContable("b", Money.parse("USD 40")),
    CuentaContable("c", Money.parse("USD 50"))
  )

  test("Balance del día") {
    implicit val r: Monoid[Money] = CuentasContablesServices.MoneyMonoid(CurrencyUnit.USD)
    val balance: Money = CuentasContablesServices.balanceDelTipo("a", cuentasContables)
    assert(Money.parse("USD 30").isEqual(balance))
  }

  test("Máxima del día") {
    implicit val r: Monoid[Money] = CuentasContablesServices.MoneyMonoid(CurrencyUnit.USD)
    val maxTipo: Money = CuentasContablesServices.maximaCuentaDelTipo("b", cuentasContables)
    assert(Money.parse("USD 40").isEqual(maxTipo))
  }

  test("Sumatoria de facturas") {
    implicit val r: Monoid[Money] = CuentasContablesServices.MoneyMonoid(CurrencyUnit.JPY)
    val balance: Money = CuentasContablesServices.balanceFacturas(List(new Factura(DateTime.now(), "A", Money.parse("JPY 10"), 0.16, Nueva())))
    assert(Money.parse("JPY 10").isEqual(balance))
  }
}
