package co.s4n.factura

import scala.concurrent.Future
import scalaz._
import Scalaz._

case class Ticket(id: String, event: String)

sealed trait Request[A]
final case class Buy(n: Int) extends Request[List[Ticket]]
final case class Discounts(ts: List[Ticket]) extends Request[List[Ticket]]
final case class VIP(ts: List[Ticket]) extends Request[List[Ticket]]
final case class Format(ts: List[Ticket]) extends Request[String]
final case class IsBonus(ts: Ticket) extends Request[Boolean]

object TicketsService {
  type Requestable[A] = Free.FreeC[Request, A]

  implicit val RequestMonad: Monad[Requestable] =
    Free.freeMonad[({ type T[A] = Coyoneda[Request, A] })#T]

  def buy(n: Int): Requestable[List[Ticket]] =
    Free.liftFC(Buy(n))

  def discounts(ts: List[Ticket]): Requestable[List[Ticket]] =
    Free.liftFC(Discounts(ts))

  def vip(ts: List[Ticket]): Requestable[List[Ticket]] =
    Free.liftFC(VIP(ts))

  def isBonus(t: Ticket): Requestable[Boolean] = {
    Free.liftFC(IsBonus(t))
  }

  def format(ts: List[Ticket]): Requestable[String] =
    Free.liftFC(Format(ts))

}

object OptionInterpreter extends NaturalTransformation[Request, Option] {

  def apply[A](request: Request[A]): Option[A] = {
    request match {
      case b: Buy => Some(List(Ticket("", "")))
      case d: Discounts => Some(List(Ticket("", "")))
      case v: VIP => Some(List(Ticket("", "")))
      case ib: IsBonus => Some(true)
      case f: Format => Some("")
    }
  }
}

object ListInterpreter extends NaturalTransformation[Request, List] {

  def apply[A](request: Request[A]): List[A] = {
    request match {
      case b: Buy => List(List(Ticket("", "")))
      case d: Discounts => List(List(Ticket("", "")))
      case v: VIP => List(List(Ticket("", "")))
      case ib: IsBonus => List(true)
      case f: Format => List("")
    }
  }
}
