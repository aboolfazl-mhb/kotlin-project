fun main() {
    println("Enter the length of the first side of the triangle:")
    val side1 = readLine()?.toDoubleOrNull()
    println("Enter the length of the second side of the triangle:")
    val side2 = readLine()?.toDoubleOrNull()
    println("Enter the length of the third side of the triangle:")
    val side3 = readLine()?.toDoubleOrNull()

    if (side1 != null && side2 != null && side3 != null) {
        if (side1 == side2 && side2 == side3) {
            println("It's an equilateral triangle")
        } else if (side1 == side2 || side2 == side3 || side1 == side3) {
            println("It's an isosceles triangle")
        } else {
            println("It's a scalene triangle")
        }
    } else {
    println("Invalid input. Please enter valid side lengths for the triangle.")
    }
}