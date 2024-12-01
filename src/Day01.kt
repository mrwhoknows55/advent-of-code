import kotlin.math.abs

fun main() {
    val sampleInput = runCatching { readInput("Day01_test") }.onFailure { error(it) }.getOrElse { emptyList() }
    val realInput = runCatching { readInput("Day01") }.onFailure { error(it) }.getOrElse { emptyList() }

    val (testLeftList, testRightList) = splitIntoLeftAndRight(sampleInput)
    println("Total distance for sample input: ${calculateDistance(testLeftList, testRightList)}")
    println("Similarity Score for test input: ${calculateSimilarityScore(testLeftList, testRightList)}")

    val (leftList, rightList) = splitIntoLeftAndRight(realInput)
    println("Total distance for real input: ${calculateDistance(leftList, rightList)}")
    println("Similarity Score for test input: ${calculateSimilarityScore(leftList, rightList)}")

}

fun calculateDistance(left: List<Int>, right: List<Int>) =
    left.sorted().zip(right.sorted()).sumOf { (leftNum, rightNum) ->
        abs(leftNum - rightNum)
    }

fun calculateSimilarityScore(left: List<Int>, right: List<Int>) =
    right.groupingBy { it }.eachCount().let { rightCountMap ->
        left.sumOf { it.times(rightCountMap[it] ?: 0) }
    }


fun splitIntoLeftAndRight(input: List<String>, delimiter: String = "   "): Pair<List<Int>, List<Int>> {
    val pairLines = input.map {
        val (left, right) = it.split(delimiter)
        left to right
    }

    return pairLines.map { (first, _) -> first.toInt() } to pairLines.map { (_, last) -> last.toInt() }
}
