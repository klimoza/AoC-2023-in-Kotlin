import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

fun main() {
    fun singleCardNumber(input: String): Int {
        val (wins, actual) = input.split("|")
        val actualNumbers = actual.split(' ').filter { it != "" }.map { it.toInt() }
        val winningNumbers = wins.split(' ', ':').filter { it != "" }.drop(2).map { it.toInt() }.toSet()
        return actualNumbers.count { it in winningNumbers }
    }

    fun getScore(numberOfWins: Int): Int {
        return if (numberOfWins == 0) 0 else 2.0.pow(numberOfWins - 1).toInt()
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {str -> getScore(singleCardNumber(str)) }
    }

    fun part2(input: List<String>): Int {
        var sm = 0
        val copies = MutableList<Int>(input.size) { 1 }
        for(i in input.indices) {
            sm += copies[i]
            val numberOfWins = singleCardNumber(input[i])
            val cardsLeft = input.size - i - 1
            for(j in i+1 until i + min(cardsLeft, numberOfWins) + 1){
                copies[j] += copies[i]
            }
        }
        return sm
    }

    val testInput1 = readInput("Day04_example1")
    check (part1(testInput1) == 13)

    val testInput2 = readInput("Day04_example2")
    check(part2(testInput2) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
