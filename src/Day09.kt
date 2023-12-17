fun main() {
    fun getLastElement(input: List<Int>): Int {
        if(input.all { it == 0 })
            return 0
        val diffs = input.zipWithNext().map { (a, b) -> b - a }
        return getLastElement(diffs) + input.last()
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { it -> getLastElement(it.split(" ").map { it.toInt() }) }
    }

    fun getFirstElement(input: List<Int>): Int {
        if(input.all { it == 0 })
            return 0
        val diffs = input.zipWithNext().map { (a, b) -> b - a }
        return input.first() - getFirstElement(diffs)
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { it -> getFirstElement(it.split(" ").map { it.toInt() }) }
    }

    val testInput1 = readInput("Day09_example1")
    check (part1(testInput1) == 114)

    val testInput2 = readInput("Day09_example2")
    check(part2(testInput2) == 2)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
