import java.util.concurrent.Executors

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

/**
 *  Batch traverse
 *  Написать функцию batch traverse которая будет для Seq[Int], f: Int => Future[Int]
 *  выдавать Future[Seq[Int]] в которой Future над элементами будут запускаться не сразу а батчами размера size
 *  Если работа с Future вызвает трудности, то можно использовать cats-effect IO, ZIO или monix Task
 */
object BatchTraverse extends App {
  //implicit val ctx: ExecutionContext = scala.concurrent.ExecutionContext.fromExecutor(Executors.newFixedThreadPool(n))
  implicit val ctx: ExecutionContext = scala.concurrent.ExecutionContext.global

  def func(i: Int): Future[Int] = Future {
    println(s"running func($i)")
    Thread.sleep(1500)
    i * 100
  }

  def batchTraverse(in: Seq[Int], size: Int)(f: Int => Future[Int]): Future[Seq[Int]] =
      in.grouped(size).map(batch => () => Future.traverse(batch)(f)).foldLeft(Future.successful(Seq[Int]())) {
      (FutereOfSeq, batchF) => for {
        myseq <- FutereOfSeq
        batch <- batchF()
      } yield myseq ++ batch
    }

  val in = 1 to 12
  val size = 4

  val res = batchTraverse(in, size)(func)
  println(Await.result(res, Duration.Inf))
}
