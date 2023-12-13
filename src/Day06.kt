import kotlin.math.*
import kotlin.time.times

fun main() {
    fun getTheRange(n: Long, d: Long): Long {
        val D = sqrt(n.toDouble() * n - 4 * d)
        val x1 = (n - D) / 2
        val x2 = (n + D) / 2
        var l = max(0, ceil(x1).toLong())
        var r = min(n, floor(x2).toLong())
        if (l * l - l * n + d >= 0)
            l += 1
        if(r * r - r * n + d >= 0)
            r -= 1
        return max(0, r - l + 1)
    }

    fun part1(input: List<String>): Long {
        val ns = input[0].split(" ").filter { it != "" }.drop(1).map { it.toLong() }
        val ds = input[1].split(" ").filter { it != "" }.drop(1).map { it.toLong() }
        var ans: Long = 1
        for (i in ns.indices)
            ans *= getTheRange(ns[i], ds[i])
        return ans
    }

    fun part2(input: List<String>): Long {
        val ns = input[0].split(" ").filter { it != "" }.drop(1).joinToString("").toLong()
        val ds = input[1].split(" ").filter { it != "" }.drop(1).joinToString("").toLong()
        return getTheRange(ns, ds)
    }

    val testInput1 = readInput("Day06_example1")
    check (part1(testInput1) == 288.toLong())

    val testInput2 = readInput("Day06_example2")
    check(part2(testInput2) == 71503.toLong())

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
