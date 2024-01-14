import java.time.LocalTime
import java.time.temporal.ChronoUnit

fun calculateElapsedTime(hour: Int, minute: Int, second: Int): String {
    val inputTime = LocalTime.of(hour, minute, second)
    val elapsedTimeHours = LocalTime.MIDNIGHT.until(inputTime, ChronoUnit.HOURS)
    val elapsedTimeMinutes = LocalTime.MIDNIGHT.until(inputTime,ChronoUnit.MINUTES)
    val elapsedTimeSeconds = LocalTime.MIDNIGHT.until(inputTime,ChronoUnit.SECONDS)
    return "$elapsedTimeHours hours, $elapsedTimeMinutes minutes, and $elapsedTimeSeconds seconds"
}

fun main() {
    try {
        while(true) {
            println("Enter the time in this format | hour-minute-second | and | exit |:")
            val hourMinuteSecond = readLine()!!.split('-')
            val hour = hourMinuteSecond.getOrNull(0)?.toInt() ?: throw NumberFormatException("Invalid hour")
            val minute = hourMinuteSecond.getOrNull(1)?.toInt() ?: throw NumberFormatException("Invalid minute")
            val second = hourMinuteSecond.getOrNull(2)?.toInt() ?: throw NumberFormatException("Invalid second")
    
            val elapsedTime = calculateElapsedTime(hour, minute, second)
            println("\nThe time $hour:$minute:$second is $elapsedTime into the day.")
        }
    } catch (e: NumberFormatException) {
        println("Invalid input. Please enter valid numbers for hour, minute, and second.")
    }
}