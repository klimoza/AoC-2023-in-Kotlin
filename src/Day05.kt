import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

fun main() {
    fun getSeedList(input: String): List<Long> {
        return input.split(' ').drop(1).map { it.toLong() }
    }

    fun goThroughMap(x: Long, mp: List<Triple<Long, Long, Long>>): Long {
        for(v in mp){
            if(x >= v.second && x < v.second + v.third)
                return v.first + x - v.second
        }
        return x
    }

    fun goThroughMaps(x: Long, mp: List<List<Triple<Long, Long, Long>>>): Long {
        var ans = x
        for(v in mp){
            ans = goThroughMap(ans, v)
        }
        return ans
    }

    fun part1(input: List<String>): Long {
        val seeds = getSeedList(input[0])
        val mp: MutableList<MutableList<Triple<Long, Long, Long>>> = mutableListOf()
        for (str in input.drop(1)) {
            if (str.isEmpty()) {
                mp.add(mutableListOf())
            } else if(str.last() != ':') {
                val (a, b, c) = str.split(' ').map { it.toLong() }
                mp[mp.size - 1].add(Triple(a, b, c))
            }
        }
        return seeds.minOf { goThroughMaps(it, mp) }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput1 = readInput("Day05_example1")
    check (part1(testInput1) == 35.toLong())

//    val testInput2 = readInput("Day05_example2")
//    check(part2(testInput2) == 30)

    val input = readInput("Day05")
    part1(input).println()
//    part2(input).println()
}
