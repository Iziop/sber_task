import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object teset {
  def main(args: Array[String]): Unit = {
    val dateStr = "2021-06-13"
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDate = LocalDate.parse(dateStr, formatter)

    val date = new Date(200)
    println(localDate)
  }
}
