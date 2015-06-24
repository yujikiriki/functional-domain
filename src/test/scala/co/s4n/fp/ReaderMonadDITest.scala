package co.s4n.fp

import org.scalatest.FunSuite
import scala.concurrent.Future
import scalaz._
import scala.concurrent.ExecutionContext.Implicits.global

class ReaderMonadDITest extends FunSuite {

  test( "Repository DI" ) {
    val u1 = new UserServicesClient( ) with DBUserRepositoryModule
    u1.testMethod( ).map( u => assert( User( Some( "DB" ), "Test" ) === u ) )

    val u2 = new UserServicesClient( ) with FileUserRepositoryModule
    u2.testMethod( ).map( u => assert( User( Some( "File" ), "Test" ) === u ) )
    ()
  }

}

case class User( id: Option[String], name: String )

sealed trait UserRepository {
  def get( id: Int ): Future[User]

  def add( user: User ): Future[User]
}

class DBUserRepository( ) extends UserRepository {
  override def get( id: Int ): Future[User] = Future.successful( User( Some( "DB" ), "DB" ) )

  override def add( user: User ): Future[User] = Future.successful( user.copy( id = Some( "DB" ) ) )
}

class FileUserRepository( ) extends UserRepository {
  override def get( id: Int ): Future[User] = Future.successful( User( Some( "File" ), "File" ) )

  override def add( user: User ): Future[User] = Future.successful( user.copy( id = Some( "File" ) ) )
}

object UserServices {

  def getUser( id: Int ): Reader[UserRepository, Future[User]] = Reader {
    ( repository: UserRepository ) => repository.get( id )
  }

  def createUser( name: String ): Reader[UserRepository, Future[User]] = Reader {
    ( repository: UserRepository ) => repository.add( User( None, name ) )
  }
}

trait UserRepositoryModule {
  def repository: UserRepository
}

trait DBUserRepositoryModule extends UserRepositoryModule {
  def repository: UserRepository = new DBUserRepository( )
}

trait FileUserRepositoryModule extends UserRepositoryModule {
  def repository: UserRepository = new FileUserRepository( )
}

class UserServicesClient {
  this: UserRepositoryModule =>

  def testMethod( ): Future[User] = {
    val ur: Reader[UserRepository, Future[User]] = for {
      u1 <- UserServices.createUser( "Test" )
      u2 <- UserServices.getUser( 1 )
    } yield u2
    ur.run( repository )
  }

}