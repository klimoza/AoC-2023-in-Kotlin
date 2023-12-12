import kotlin.math.max

fun main() {
    fun maxBalls(input: String, color: String): Int {
        val modifiedInput = input.split(':', ',', ';', ' ').filter { it != "" }.drop(2)
        var maximumCount = 0
        for (i in modifiedInput.indices) {
            if (i % 2 == 0 && modifiedInput[i + 1] == color) {
                maximumCount = max(maximumCount, modifiedInput[i].toInt())
            }
        }
        return maximumCount
    }

    fun part1(input: List<String>): Int {
        return input.indices.filter { i ->
            val reds = maxBalls(input[i], "red")
            val greens = maxBalls(input[i], "green")
            val blues = maxBalls(input[i], "blue")
            reds <= 12 && greens <= 13 && blues <= 14
        }.sumOf { i -> i + 1 }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { str ->
            val reds = maxBalls(str, "red")
            val greens = maxBalls(str, "green")
            val blues = maxBalls(str, "blue")
            reds * greens * blues
        }
    }

    val testInput1 = readInput("Day02_example1")
    check(part1(testInput1) == 8)

    val testInput2 = readInput("Day02_example2")
    check(part2(testInput2) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
