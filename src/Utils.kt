import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/inputs/$name.txt").readText().trim().lines()

/**
 * Reads lines from src/inputs/Day${dayNumber}.txt file
 * @param dayNumber: number of the day: from 1 to 25
 * @param testInput: for testInput files: true otherwise false
 */
fun readDayInput(dayNumber: Int, testInput: Boolean = false): List<String> {
    require(dayNumber in 1..25) { "dayNumber must be in the range of 1 to 25" }
    val number = String.format("%02d", dayNumber)
    return runCatching { readInput("Day$number${if (testInput) "_test" else ""}") }.onFailure { error(it) }
        .getOrElse { emptyList() }
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16).padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
