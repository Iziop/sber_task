import cats.effect.{ExitCode, IO, IOApp}
import doobie.util.transactor.Transactor
import doobie.implicits._

object task5 extends IOApp{

  implicit class Debbuger[A](io: IO[A]){
    def debug: IO[A] = io.map{a =>
      println(s"[${Thread.currentThread().getName}] $a")
      a
    }

  }

  val mytransactor : Transactor[IO] = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql:mydata",
    "docker",
    "docker"
  )

  def findAmountOfCallsbySql(): IO[List[(String,Int)]] = {
    val query = sql"select operators.login, count(calls.id) as calls_sum from calls right join operators on calls.operator_id=operators.id and calls.dates = '2022-01-05' group by operators.login".query[(String,Int)]
    val action = query.to[List]
    action.transact(mytransactor)
  }

  override def run(args: List[String]): IO[ExitCode] = findAmountOfCallsbySql.debug.as(ExitCode.Success)
}
