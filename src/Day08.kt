import java.util.LinkedList

fun main() {
    fun part1(inp: List<String>): Int {
        val instructions = inp[0]
        val input = inp.drop(2)

        val id = mutableMapOf<String, Int>()
        input.forEachIndexed { index, s ->
            id[s.split(" ")[0]] = index
        }
        val edges = MutableList(input.size) { mutableListOf<Int>() }
        input.forEachIndexed { index, s ->
            edges[index] = s.split(" ", "(", ",", ")").drop(2).filter { it != "" }.map { id[it]!! }.toMutableList()
        }
        val dst = MutableList(input.size) { MutableList(instructions.length) { 2e9.toInt() } }
        val qu = LinkedList<Pair<Int, Int>>()
        qu.add(Pair(id["AAA"]!!, 0))
        dst[id["AAA"]!!][0] = 0
        val t = id["ZZZ"]!!
        while (qu.isNotEmpty()) {
            val (v, nm) = qu.poll()
            val u = if (instructions[nm] == 'L') edges[v][0] else edges[v][1]
            val nxtId = if (nm + 1 == instructions.length) 0 else nm + 1
            if (dst[u][nxtId] > dst[v][nm] + 1) {
                dst[u][nxtId] = dst[v][nm] + 1
                qu.add(Pair(u, nxtId))
                if(u == t)
                    return dst[u][nxtId]
            }
        }
        return -1
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput1a = readInput("Day08_example1a")
    check (part1(testInput1a) == 2)

    val testInput1b = readInput("Day08_example1b")
    check (part1(testInput1b) == 6)

//    val testInput2 = readInput("Day08_example2")
//    check(part2(testInput2) == 5905)

    val input = readInput("Day08")
    part1(input).println()
//    part2(input).println()
}
