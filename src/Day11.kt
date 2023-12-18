import java.util.LinkedList
import java.util.Stack
import kotlin.math.abs

fun main() {
    fun expandGalaxies(input: List<String>, coef: Long): Long {
        val n = input.size
        val m = input[0].length
        val rows = MutableList(n) { false }
        val cols = MutableList(m) { false }
        for(i in input.indices) {
            for(j in input.indices) {
                if(input[i][j] == '#') {
                    rows[i] = true
                    cols[j] = true
                }
            }
        }
        val c = mutableListOf<Pair<Long, Long>>()
        var cur_i = 0.toLong()
        for(i in rows.indices) {
            var cur_j = 0.toLong()
            for(j in cols.indices) {
                if(input[i][j] == '#')
                    c.add(Pair(cur_i, cur_j))
                cur_j++
                if(!cols[j])
                    cur_j += coef - 1
            }
            cur_i++
            if(!rows[i]) {
                cur_i += coef - 1
            }
        }
        var ans = 0.toLong()
        for(i in c.indices) {
            for(j in i+1 until c.size) {
                val (x1, y1) = c[i]
                val (x2, y2) = c[j]
                ans += abs(x1 - x2) + abs(y1 - y2)
            }
        }
        return ans
    }

    fun part1(input: List<String>): Long {
        return expandGalaxies(input, 2.toLong())
    }

    fun part2(input: List<String>): Long {
        return expandGalaxies(input, 1e6.toLong())
    }

    val testInput1 = readInput("Day11_example1")
    check (part1(testInput1) == 374.toLong())
    check(expandGalaxies(testInput1, 10.toLong()) == 1030.toLong())
    check(expandGalaxies(testInput1, 100.toLong()) == 8410.toLong())


    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}
