fun main() {

    print("لطفاً وزن طلا را به گرم وارد کنید: ")
    val weight = readLine()!!.toDouble()

    val makingChargePercentage = 7
    val valueAddedPercentage = 9

    val makingCharge = weight * (makingChargePercentage / 100.0)
    val valueAddedTax = weight * (valueAddedPercentage / 100.0)

    val finalAmount = weight + makingCharge + valueAddedTax
    
    println("وزن طلا: $weight گرم")
    println("اجرت ($makingChargePercentage٪): $makingCharge تومان")
    println("ارزش افزوده ($valueAddedPercentage٪): $valueAddedTax تومان")
    println("مبلغ فاکتور نهایی: $finalAmount تومان")
}