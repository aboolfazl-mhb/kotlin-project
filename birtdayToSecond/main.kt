import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun main() {
    println("Please enter your date of birth (year-month-day hour:minute:second):")
    val userInput = readLine()!!

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val birthdayDateTime = LocalDateTime.parse(userInput, formatter)

    val difference = java.time.Duration.between(LocalDateTime.now(), birthdayDateTime)

    println("Your date of birth in units:")
    println("Second: ${difference.seconds}")
    println("minutes: ${difference.toMinutes()}")
    println("hour: ${difference.toHours()}")
    println("day: ${difference.toDays()}")
    println("month: ${difference.toDays() / 30}")
    println("year: ${difference.toDays() / 365}")
}
