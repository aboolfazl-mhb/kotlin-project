fun main() {
    val banknote2 = 2000
    val banknote5 = 5000

    print("Please enter the required amount in Tomans: ")
    val requiredAmount = readLine()!!.toInt()

    val numBanknotes5 = requiredAmount / banknote5

    val remainingAmount = requiredAmount % banknote5

    val numBanknotes2 = remainingAmount / banknote2

    println("To pay $requiredAmount:")
    println("$numBanknotes5, bills of 5 thousand tomans")
    println("$numBanknotes2 2 thousand toman banknotes")
}
