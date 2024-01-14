data class Student(val firstName: String, val lastName: String, val grade: Double)

fun main() {
    println("Enter the number of students:")
    val numStudents = readLine()!!.toInt()
    val students = mutableListOf<Student>()

    for (i in 1..numStudents) {
        println("Enter the first name of student $i:")
        val firstName = readLine()!!
        println("Enter the last name of student $i:")
        val lastName = readLine()!!
        println("Enter the grade of student $i:")
        val grade = readLine()!!.toDouble()
        students.add(Student(firstName, lastName, grade))
        // val test = Student(firstName, lastName, grade)
        // println(test)
    }

    val maxGradeStudent = students.maxByOrNull { it.grade }
    val minGradeStudent = students.minByOrNull { it.grade }

    if (maxGradeStudent != null && minGradeStudent != null) {
        println("Student with the + grade: ${maxGradeStudent.firstName} ${maxGradeStudent.lastName} - Grade: ${maxGradeStudent.grade}")
        println("Student with the - grade: ${minGradeStudent.firstName} ${minGradeStudent.lastName} - Grade: ${minGradeStudent.grade}")
    }
}
