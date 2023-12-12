import kotlin.math.max

fun main() {
    val coords: List<Pair<Int, Int>> = listOf(
        Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(1, -1), Pair(1, 1), Pair(-1, 0), Pair(-1, -1), Pair(-1, 1)
    )

    fun isSymbol(c: Char): Boolean {
        return c != '.' && !c.isDigit()
    }

    fun inside(i: Int, j: Int, input: List<String>): Boolean {
        return i >= 0 && i < input.size && j >= 0 && j < input[i].length
    }

    fun part1(input: List<String>): Int {
        var sm: Int = 0
        for (i in input.indices) {
            var currentNumber = 0
            var adj = false
            for (j in input[i].indices) {
                if (input[i][j].isDigit()) {
                    currentNumber = currentNumber * 10 + input[i][j].toString().toInt()
                    for (coord in coords.map { Pair(it.first + i, it.second + j) }) {
                        if (inside(coord.first, coord.second, input) && isSymbol(input[coord.first][coord.second])) {
                            adj = true
                            break
                        }
                    }
                } else {
                    if (currentNumber != 0 && adj)
                        sm += currentNumber
                    currentNumber = 0
                    adj = false
                }
            }
            if(currentNumber != 0 && adj)
                sm += currentNumber
        }
        return sm
    }

    fun isGear(c: Char): Boolean {
        return c == '*'
    }

    fun part2(input: List<String>): Int {
        val gearCounts = MutableList(input.size) { MutableList(input[0].length) { 0 } }
        val gearRatios = MutableList(input.size) { MutableList(input[0].length) { 1 } }
        var sm = 0
        for (i in input.indices) {
            var currentNumber = 0
            val adj: MutableSet<Pair<Int, Int>> = mutableSetOf()
            for (j in input[i].indices) {
                if (input[i][j].isDigit()) {
                    currentNumber = currentNumber * 10 + input[i][j].toString().toInt()
                    for (coord in coords.map { Pair(it.first + i, it.second + j) }) {
                        if (inside(coord.first, coord.second, input) && isGear(input[coord.first][coord.second])) {
                            adj += coord
                        }
                    }
                } else {
                    for (coord in adj) {
                        gearCounts[coord.first][coord.second] += 1
                        if(gearCounts[coord.first][coord.second] <= 2)
                            gearRatios[coord.first][coord.second] *= currentNumber
                    }
                    currentNumber = 0
                    adj.clear()
                }
            }
            for (coord in adj) {
                gearCounts[coord.first][coord.second] += 1
                if(gearCounts[coord.first][coord.second] <= 2)
                    gearRatios[coord.first][coord.second] *= currentNumber
            }
        }
        for (i in gearRatios.indices) {
            for (j in gearRatios[i].indices) {
                if(gearCounts[i][j] == 2)
                    sm += gearRatios[i][j]
            }
        }
        return sm
    }

    val testInput1 = readInput("Day03_example1")
    check (part1(testInput1) == 4361)

    val testInput2 = readInput("Day03_example2")
    check(part2(testInput2) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
