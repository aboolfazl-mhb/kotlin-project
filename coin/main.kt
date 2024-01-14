fun main() {
    print("Please enter the amount in Tomans:")
    val amount = readLine()!!.toInt()

    val numCoins5 = amount / 5

    val remainingAmount1 = amount % 5

    val numCoins2 = remainingAmount1 / 2

    val remainingAmount2 = remainingAmount1 % 2

    val numCoins1 = remainingAmount2

    println("Minimum number of coins required:")
    println("$numCoins5 Number of 5 Toman coins")
    println("$numCoins2 Number of 2 Toman coins")
    println("$numCoins1 Number of 1 Toman coins")
}
