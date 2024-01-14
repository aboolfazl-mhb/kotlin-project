fun main() {
    val numbers = mutableListOf<Int>()

    for (i in 1..10) {
        print("Enter number $i: ")
        val input = readLine()!!.toInt()
        numbers.add(input)
    }

    numbers.sort()

    val secondLargest = numbers[numbers.size - 2]

    val secondSmallest = numbers[1]

    println("The second largest number: $secondLargest")
    println("The second smallest number: $secondSmallest")
}
