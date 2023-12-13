import kotlin.math.*

fun main() {
    fun getType(str: String): Int {
        val cnt: MutableMap<Char, Int> = mutableMapOf()
        for(c in str)
            cnt[c] = cnt.getOrDefault(c, 0) + 1
        return if(cnt.size == 5)
            1
        else if(cnt.size == 4)
            2
        else if(cnt.size == 3 && !cnt.values.any { it == 3.toInt() })
            3
        else if(cnt.size == 3)
            4
        else if(cnt.size == 2 && !cnt.values.any { it == 4.toInt() })
            5
        else if(cnt.size == 2)
            6
        else
            7
    }

    fun part1(input: List<String>): Int {
        val modifiedList = input.map { str ->
            val (hand, bid) = str.split(" ").filter { it != " " }
            val type = getType(hand)
            val modifiedHand = hand.toList().map {
                when(it) {
                    'A' -> 14
                    'K' -> 13
                    'Q' -> 12
                    'J' -> 11
                    'T' -> 10
                    else -> it.digitToInt()
                }.toInt()
            }
            Triple(type, modifiedHand, bid)
        }
        val sortedList = modifiedList.sortedWith ( object : Comparator <Triple<Int, List<Int>, String>>{
            override fun compare(o1: Triple<Int, List<Int>, String>, o2: Triple<Int, List<Int>, String>): Int {
                if(o1.first != o2.first)
                    return o1.first.compareTo(o2.first)
                if(o1.second != o2.second)
                    return o1.second.zip(o2.second).map { it.first.compareTo(it.second) }.first { it != 0 }
                return 0
            }
        })
        return sortedList.mapIndexed { index, triple -> (index + 1) * triple.third.toInt()  }.sum()
    }

    fun getType2(str: String): Int {
        val cnt: MutableMap<Char, Int> = mutableMapOf()
        for(c in str)
            cnt[c] = cnt.getOrDefault(c, 0) + 1
        val numberOfJokers = cnt.getOrDefault('J', 0)
        if(cnt.size == 1)
            return 7
        val maxCard = cnt.filter { it.key != 'J' }.maxBy { it.value }.key
        cnt.remove('J')
        cnt[maxCard] = cnt.getOrDefault(maxCard, 0) + numberOfJokers
        return if(cnt.size == 5)
            1
        else if(cnt.size == 4)
            2
        else if(cnt.size == 3 && !cnt.values.any { it == 3.toInt() })
            3
        else if(cnt.size == 3)
            4
        else if(cnt.size == 2 && !cnt.values.any { it == 4.toInt() })
            5
        else if(cnt.size == 2)
            6
        else
            7
    }

    fun part2(input: List<String>): Int {
        val modifiedList = input.map { str ->
            val (hand, bid) = str.split(" ").filter { it != " " }
            val type = getType2(hand)
            val modifiedHand = hand.toList().map {
                when(it) {
                    'A' -> 14
                    'K' -> 13
                    'Q' -> 12
                    'J' -> 1
                    'T' -> 10
                    else -> it.digitToInt()
                }.toInt()
            }
            Triple(type, modifiedHand, bid)
        }
        val sortedList = modifiedList.sortedWith ( object : Comparator <Triple<Int, List<Int>, String>>{
            override fun compare(o1: Triple<Int, List<Int>, String>, o2: Triple<Int, List<Int>, String>): Int {
                if(o1.first != o2.first)
                    return o1.first.compareTo(o2.first)
                if(o1.second != o2.second)
                    return o1.second.zip(o2.second).map { it.first.compareTo(it.second) }.first { it != 0 }
                return 0
            }
        })
        return sortedList.mapIndexed { index, triple -> (index + 1) * triple.third.toInt()  }.sum()

    }

    val testInput1 = readInput("Day07_example1")
    check (part1(testInput1) == 6440)

    val testInput2 = readInput("Day07_example2")
    check(part2(testInput2) == 5905)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
