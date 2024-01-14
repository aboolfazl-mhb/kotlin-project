import java.time.LocalDate
import java.time.chrono.HijrahChronology
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun miladiTogamari(gYear: Int, gMonth: Int, gDay: Int): String {
    val gregorianDate = LocalDate.of(gYear, gMonth, gDay)
    val hijrahDate = HijrahChronology.INSTANCE.date(gregorianDate)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
    return hijrahDate.format(formatter)
}

fun gamariTomiladi(jYear: Int, jMonth: Int, jDay: Int): String {
    val hijrahDate = HijrahChronology.INSTANCE.date(jYear, jMonth, jDay)
    val gregorianDate = LocalDate.from(hijrahDate)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
    return gregorianDate.format(formatter)
}

fun shamsTOmilad(gy: Int, gm: Int, gd: Int) {
    if (gm >= 1 && gm <= 10){
        val sum = gy + 621;
        println("تاریخ میلادی شما" + sum);
    }else if (gm >= 11  && gd > 0) {
        val sum = gy + 622;
        println("تاریخ میلادی شما" + sum);
    }
}

fun miladToshams(gy: Int, gm: Int, gd: Int) {
    if (gm >= 4 && gm <= 12){
        val sum = gy - 621;
        println(" تاریخ شمسی خورشیدی شما" + sum);
    }else if (gm >= 1 && gm <=3  && gd > 0) {
        val sum = gy - 622;
        println(" تاریخ شمسی خورشیدی شما" + sum);
    }
}


fun main() {
    while(true){

        print("برای میلادی به شمسی ۱ و برای شمسی به میلادی ۲:")
        val user = readLine()
        
        when (user) {
            // "2023-11-20"
    
            "1" -> {
                print("لطفاً تاریخ میلادی را وارد کنید: ")
                val userInput_miladi = readLine()
            
                if (userInput_miladi != null) {
                    try {
    
                        val miladiParts = userInput_miladi.split("-")
                        val gYear = miladiParts[0].toInt()
                        val gMonth = miladiParts[1].toInt()
                        val gDay = miladiParts[2].toInt()
                        val gamariResult = miladiTogamari(gYear, gMonth, gDay)
                        println("\nتاریخ میلادی: $userInput_miladi")
                        println("تاریخ قمری: $gamariResult\n")
    
                    } catch (e: NumberFormatException) {
                        println("ورودی نامعتبر است..")
                    }
                } else {
                    println("ورودی خالی است.")
                }
            }
            "2" -> {
                //"1402-08-29"
    
                print("لطفاً تاریخ شمسی را وارد کنید: ")
                val userInput_gamari = readLine()
            
                if (userInput_gamari != null) {
                    try {
    
                        val gamariParts = userInput_gamari.split("-")
                        val jYear = gamariParts[0].toInt()
                        val jMonth = gamariParts[1].toInt()
                        val jDay = gamariParts[2].toInt()
    
                        val miladiResult = gamariTomiladi(jYear, jMonth, jDay)
                        println("\nتاریخ شمسی: $userInput_gamari")
                        println("تاریخ میلادی: $miladiResult\n")
    
                    } catch (e: NumberFormatException) {
                        println("ورودی نامعتبر است..")
                    }
                } else {
                    println("ورودی خالی است.")
                }
            }
            "3" -> {
                //"1402-08-29"
    
                print("لطفاً تاریخ شمسی را وارد کنید: ")
                val userInput_gamari = readLine()
            
                if (userInput_gamari != null) {
                    try {
    
                        val gamariParts = userInput_gamari.split("-")
                        val jYear = gamariParts[0].toInt()
                        val jMonth = gamariParts[1].toInt()
                        val jDay = gamariParts[2].toInt()
    
                        shamsTOmilad(jYear, jMonth, jDay)
    
                    } catch (e: NumberFormatException) {
                        println("ورودی نامعتبر است..")
                    }
                } else {
                    println("ورودی خالی است.")
                }
            }
            "4" -> {
                // "2023-11-20"
    
                print("لطفاً تاریخ میلادی را وارد کنید: ")
                val userInput_gamari = readLine()
            
                if (userInput_gamari != null) {
                    try {
    
                        val gamariParts = userInput_gamari.split("-")
                        val jYear = gamariParts[0].toInt()
                        val jMonth = gamariParts[1].toInt()
                        val jDay = gamariParts[2].toInt()
    
                        miladToshams(jYear, jMonth, jDay)
    
                    } catch (e: NumberFormatException) {
                        println("ورودی نامعتبر است..")
                    }
                } else {
                    println("ورودی خالی است.")
                }
            }
            else -> { 
                print("\nتاریخ دیگری فعلا وجود ندارد \n")
                
            }
        }
    }
}
