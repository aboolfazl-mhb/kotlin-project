fun main() {
    var products: Array<Pair<String, Int>> = emptyArray()

    print("تعداد کالاها: ")
    val numProducts = readLine()!!.toInt()
    var all_ar = 0.0;
    for (i in 1..numProducts) {
        print("نام کالا $i: ")
        val productName = readLine()!!
        print("قیمت کالا $i: ")
        val productPrice = readLine()!!.toInt()

        products += productName to productPrice

        val v = 0.09
        val arz = productPrice * v
        val sum = arz + productPrice
        all_ar += sum
        println("arzsh afzude kala "+productName+" : "+sum)
    }

    val allprice = products.sumOf { it.second }

    println("geymat bedun arzsh afzude $allprice")
    println("geymat ba arzsh afzude $all_ar")
}