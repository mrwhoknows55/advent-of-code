import kotlin.math.abs

fun main() {
    val sampleInput = readDayInput(dayNumber = 2, testInput = true)
    val realInput = readDayInput(dayNumber = 2, testInput = false)

    println("Safe reports count for sample input: ${countSafeReports(sampleInput)}")
    println("Safe reports count for real input: ${countSafeReports(realInput)}")
}

fun countSafeReports(reports: List<String>): Int {
    return reports.count { report ->
        val levels = report.split(" ").map { it.toInt() }

        val allIncreasing = levels.zipWithNext { a, b -> b >= a + 1 && b <= a + 3 }.all { it }
        val allDecreasing = levels.zipWithNext { a, b -> a >= b + 1 && a <= b + 3 }.all { it }

        if (allIncreasing || allDecreasing) {
            levels.zipWithNext { a, b -> abs(a - b) >= 1 }.all { it }
        } else {
            false
        }
    }
}