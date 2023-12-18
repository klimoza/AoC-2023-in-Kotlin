import kotlin.math.min

fun main() {
    fun processLine(line: String): Long {
        val (fieldString, numberString) = line.split(" ")
        val field = "$fieldString."
        val numbers = numberString.split(",").map { it.toInt() }
        val n = field.length
        val m = numbers.size
        val dp = MutableList(n) { MutableList(m + 1) { 0.toLong() } }
        for(j in 0..m) {
            for(i in 0..<n) {
                if(j == 0) {
                    if(field[i] == '#') {
                        dp[i][j] = 0
                    } else {
                        if(i == 0)
                            dp[i][j] = 1
                        else
                            dp[i][j] = dp[i - 1][j]
                    }
                } else {
                    if(i == 0 || field[i] == '#')
                        dp[i][j] = 0
                    else {
                        dp[i][j] = dp[i - 1][j]
                        if(i + 1 > numbers[j - 1]) {
                            var fl = true
                            for (k in 0..<numbers[j - 1]) {
                                if(field[i - k - 1] == '.') {
                                    fl = false
                                    break
                                }
                            }
                            if(fl && j == 1 && i - 1 - numbers[0] == -1)
                                dp[i][j] += 1.toLong()
                            else if(fl && i - 1 - numbers[j - 1] >= 0)
                                dp[i][j] += dp[i - 1 - numbers[j - 1]][j - 1]
                        }
                    }
                }
            }
        }
        return dp[n - 1][m]
    }

    fun part1(input: List<String>): Long {
        return input.sumOf { processLine(it) }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { line ->
            val (fieldString, numberString) = line.split(" ")
            var newFieldString = fieldString
            var newNumberString = numberString
            for(i in 0..3) {
                newFieldString += "?$fieldString"
                newNumberString += ",$numberString"
            }
            processLine("$newFieldString $newNumberString")
        }
    }

    val testInput1 = readInput("Day12_example1")
    check (part1(testInput1) == 21.toLong())

    val testInput2 = readInput("Day12_example2")
    check(part2(testInput2) == 525152.toLong())

    val input = readInput("Day12")
    part1(input).println()
    part2(input).println()
}
