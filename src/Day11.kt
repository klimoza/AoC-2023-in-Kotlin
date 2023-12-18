import java.util.LinkedList
import java.util.Stack

fun main() {
    fun part1(input: List<String>): Int {
        var ans = 0
        val n = input.size
        val m = input[0].length
        val place = MutableList<Int>(m) { 0 }
        for(i in input.indices){
            for(j in input[0].indices) {
                if(input[i][j] == '#') {
                    place[j] = i + 1
                } else if(input[i][j] == 'O') {
                    ans += n - place[j]
                    place[j]++
                }
            }
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput1 = readInput("Day11_example1")
    check (part1(testInput1) == 136)

//    val testInput2 = readInput("Day11_example2")
//    check(part2(testInput2) == 8)

    val input = readInput("Day11")
    part1(input).println()
//    part2(input).println()
}
