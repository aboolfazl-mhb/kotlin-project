fun main() {
    print("Enter the distance (in kilometers): ")
    val distance = readLine()?.toDoubleOrNull()

    print("Enter the time (in hours): ")
    val time = readLine()?.toDoubleOrNull()

    if (distance != null && time != null && time > 0) {
        val speed = calculateSpeed(distance, time)

        println("The speed is: $speed kilometers per hour")
    } else {
        println("Invalid input. Please enter valid distance and time.")
    }
}

fun calculateSpeed(distance: Double, time: Double): Double {
    return distance / time
}
