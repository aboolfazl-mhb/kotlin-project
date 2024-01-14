fun main() {
    print("Enter birthday (YYYY-MM): ")
    val birthday = readLine()

    for (i in 1..5) {

        if (birthday != null) {
            val birt = birthday.split("-")
            val month = birt.getOrNull(1)?.toInt()

            if (month != null) {
                print("Enter a month: ")
                val hads = readLine()?.toInt()

                if (hads != null && hads == month) {
                    print("\n you wine\n")
                    break
                }
            } else {
                print("Invalid birthday format.")
            }
        } else {
            print("Invalid input for birthday.")
        }
    }
}
