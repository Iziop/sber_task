import cats.effect.{ExitCode, IO, IOApp}
import doobie.util.transactor.Transactor
import doobie.implicits._

object task1 extends IOApp{

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

  def findIDbySql: IO[List[String]] = {
    val query = sql"select id from (select id,ourtimestamp,MAX(ourtimestamp) over (order by id rows between unbounded preceding and 1 preceding) as previous_max_date from id_and_timestamp) as new_table where ourtimestamp < previous_max_date".query[String]
    val action = query.to[List]
    action.transact(mytransactor)
  }

  override def run(args: List[String]): IO[ExitCode] = findIDbySql.debug.as(ExitCode.Success)
}
