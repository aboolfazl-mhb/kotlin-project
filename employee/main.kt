fun main() {
    val employeeData = mutableMapOf<String, Double>()

    print("تعداد کارمندان: ")
    val numEmployees = readLine()?.toIntOrNull() ?: 2

    for (i in 1..numEmployees) {
        print("نام کارمند $i: ")
        val name = readLine() ?: ""
        print("ساعت کاری $name: ")
        val hoursWorked = readLine()?.toDoubleOrNull() ?: 0.0
        employeeData[name] = hoursWorked
    }

    val maxHoursEmployee = employeeData.maxByOrNull { it.value }?.key

    val giftCardAmount = 50.0 

    if (maxHoursEmployee != null) {
        val message = "تبریک! شما برنده کارت هدیه $giftCardAmount دلاری شدید."
        println("\n$maxHoursEmployee:\n$message")
    } else {
        println("هیچ کارمندی یافت نشد.")
    }
}
