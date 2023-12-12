fun main() {
    fun part1(input: List<String>): Int {
        val digits = input.map { str -> str.filter { c -> c.isDigit() }.toList().map { c -> c.digitToInt() } }
        return digits.sumOf { digits ->
            digits[0] * 10 + digits.last()
        }
    }

    fun getFirstDigit(input: String): Int {
        var str = input
        while (str.isNotEmpty()) {
            if(str.startsWith("one")) {
                return 1
            } else if(str.startsWith("two")) {
                return 2
            } else if(str.startsWith("three")) {
                return 3
            } else if(str.startsWith("four")) {
                return 4
            } else if(str.startsWith("five")) {
                return 5
            } else if(str.startsWith("six")) {
                return 6
            } else if(str.startsWith("seven")) {
                return 7
            } else if(str.startsWith("eight")) {
                return 8
            } else if(str.startsWith("nine")) {
                return 9
            } else {
                if(str[0].isDigit()) {
                    return str[0].digitToInt()
                }
                str = str.drop(1)
            }
        }
        return 0
    }

    fun getLastDigit(input: String): Int {
        var str = input
        while (str.isNotEmpty()) {
            if(str.endsWith("one")) {
                return 1
            } else if(str.endsWith("two")) {
                return 2
            } else if(str.endsWith("three")) {
                return 3
            } else if(str.endsWith("four")) {
                return 4
            } else if(str.endsWith("five")) {
                return 5
            } else if(str.endsWith("six")) {
                return 6
            } else if(str.endsWith("seven")) {
                return 7
            } else if(str.endsWith("eight")) {
                return 8
            } else if(str.endsWith("nine")) {
                return 9
            } else {
                if(str.last().isDigit()) {
                    return str.last().digitToInt()
                }
                str = str.dropLast(1)
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { str ->
            val firstDigit = getFirstDigit(str)
            val lastDigit = getLastDigit(str)
            firstDigit * 10 + lastDigit
        }
    }

    val testInput1 = readInput("Day01_example1")
    check(part1(testInput1) == 142)

    val testInput2 = readInput("Day01_example2")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
