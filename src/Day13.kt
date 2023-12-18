import kotlin.math.min

fun main() {
    fun calculateRows(input: List<String>): List<List<Int>> {
        val n = input.size
        val rows = MutableList(n) { MutableList(n) { 0 } }
        for(i1 in input.indices) {
            for(i2 in (i1 + 1)..<n) {
                for(j in input[0].indices) {
                    if(input[i1][j] != input[i2][j]) {
                        rows[i1][i2] += 1
                        rows[i2][i1] += 1
                    }
                }
            }
        }
        return rows
    }

    fun calculateCols(input: List<String>): List<List<Int>> {
        val m = input[0].length
        val cols = MutableList(m) { MutableList(m) { 0 } }
        for(j1 in input[0].indices) {
            for(j2 in (j1 + 1)..<m) {
                for(i in input.indices) {
                    if(input[i][j1] != input[i][j2]) {
                        cols[j1][j2] += 1
                        cols[j2][j1] += 1
                    }
                }
            }
        }
        return cols
    }

    fun solveField(input: List<String>, numberOfSmudges: Int = 0): Int {
        val n = input.size
        val m = input[0].length
        val rows = calculateRows(input)
        val cols = calculateCols(input)
        var ans = 0
        for(i in 0..<(n - 1)) {
            var sm = 0
            for(j in 0..min(i, n - i - 2))
                sm += rows[i - j][i + j + 1]
            if(sm == numberOfSmudges)
                ans += 100 * (i + 1)
        }
        for(i in 0..<(m - 1)) {
            var sm = 0
            for(j in 0..min(i, m - i - 2))
                sm += cols[i - j][i + j + 1]
            if(sm == numberOfSmudges)
                ans += (i + 1)
        }
        return ans
    }

    fun processBatch(input: List<String>, numberOfSmudges: Int = 0): Int {
        var ans = 0
        val currentField = mutableListOf<String>()
        for(line in input) {
            if(line.isEmpty()) {
                ans += solveField(currentField, numberOfSmudges)
                currentField.clear()
            } else {
                currentField.add(line)
            }
        }
        ans += solveField(currentField, numberOfSmudges)
        return ans
    }

    fun part1(input: List<String>): Int {
        return processBatch(input)
    }

    fun part2(input: List<String>): Int {
        return processBatch(input, 1)
    }

    val testInput1 = readInput("Day13_example1")
    check (part1(testInput1) == 405)

    val testInput2 = readInput("Day13_example2")
    check(part2(testInput2) == 400)

    val input = readInput("Day13")
    part1(input).println()
    part2(input).println()
}
