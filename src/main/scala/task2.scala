
/**
  *  Есть список из интов. Подсчитать количество перемен знака в последовательности (ноль не перемена)
  *  Для List(0, 0, 1, 0, 0, 1, 0, 1) - 0 перемен знака
  *  Для List(1, 0 , -1)              - 1 перемена
  *  Для List(1, 0 , -1, 0, 0, 0, -1) - тоже 1 перемена
  */
object PositiveNegativeCount extends App {

  val in = List(1,-2,3,3,4,-2,-2,-3,1)

  def ourcount(i: Int = 0,acc:Int = 0) : Int = {
    if (i == in.size - 1) acc else if ((in(i) > 0 && in(i + 1) < 0) || (in(i) < 0 && in(i+1)>0)) ourcount(i+1,acc+1)
    else ourcount(i+1, acc)
  }

  println(ourcount())
}