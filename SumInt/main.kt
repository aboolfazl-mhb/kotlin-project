fun main() {
    print("Please enter a three-digit integer:")
    val userInput = readLine()

    if (userInput != null && userInput.matches(Regex("\\d{3}"))) {
        val sum = userInput.map { it.toString().toInt() }.sum()
        println("Sum of digits of a number $userInput: $sum")
    } else {
        println("The input is not valid. Please enter a three-digit integer.")
    }
}
