import java.util.*
import kotlin.math.max

fun main() {
    var n = 0
    var m = 0
    var g: MutableList<Stack<Int>> = mutableListOf()
    var used: MutableList<Boolean> = mutableListOf()
    var dm: MutableList<Int> = mutableListOf()
    fun id(i: Int, j: Int, dir: Char): Int {
        val x = when (dir) {
            'R' -> 0
            'D' -> 1
            'L' -> 2
            else -> 3
        }
        return i * m * 4 + j * 4 + x
    }
    fun inside(i: Int, j: Int): Boolean {
        return i in 0 until n && j in 0 until m
    }
    fun addEdge(x: Int, y: Int, oldDir: Char, i: Int, j: Int, newDir: Char){
        if(!inside(i, j)) return
        val oldId = id(x, y, oldDir)
        val nextId = id(i, j, newDir)
        g[oldId].add(nextId)
    }

    fun dfs(v: Int) {
        for(i in 0 until (n * m * 4))
            dm[i] = g[i].size - 1
        used.fill(false)
        val kek = Stack<Int>()
        kek.push(v)
        while (kek.isNotEmpty()) {
            used[kek.last()] = true
            if(dm[kek.last()] == -1) {
                kek.pop()
                continue
            }
            val u = g[kek.last()][dm[kek.last()]]
            dm[kek.last()]--
            if(used[u]) continue
            kek.push(u)
        }
    }

    fun part1(input: List<String>): Int {
        n = input.size
        m = input[0].length
        g = MutableList(n * m * 4) { Stack() }
        used = MutableList(n * m * 4) { false }
        dm = MutableList(n * m * 4) { 0 }
        for(i in input.indices) {
            for(j in input[i].indices) {
                when(input[i][j]) {
                    '.' -> {
                        addEdge(i, j, 'R', i, j + 1, 'R')
                        addEdge(i, j, 'D', i + 1, j, 'D')
                        addEdge(i, j, 'L', i, j - 1, 'L')
                        addEdge(i, j, 'U', i - 1, j, 'U')
                    }
                    '/' -> {
                        addEdge(i, j, 'R', i - 1, j, 'U')
                        addEdge(i, j, 'D', i, j - 1, 'L')
                        addEdge(i, j, 'L', i + 1, j, 'D')
                        addEdge(i, j, 'U', i, j + 1, 'R')
                    }
                    '\\' -> {
                        addEdge(i, j, 'R', i + 1, j, 'D')
                        addEdge(i, j, 'D', i, j + 1, 'R')
                        addEdge(i, j, 'L', i - 1, j, 'U')
                        addEdge(i, j, 'U', i, j - 1, 'L')
                    }
                    '|' -> {
                        addEdge(i, j, 'R', i - 1, j, 'U')
                        addEdge(i, j, 'R', i + 1, j, 'D')
                        addEdge(i, j, 'D', i + 1, j, 'D')
                        addEdge(i, j, 'L', i - 1, j, 'U')
                        addEdge(i, j, 'L', i + 1, j, 'D')
                        addEdge(i, j, 'U', i - 1, j, 'U')
                    }
                    '-' -> {
                        addEdge(i, j, 'R', i, j + 1, 'R')
                        addEdge(i, j, 'D', i, j - 1, 'L')
                        addEdge(i, j, 'D', i, j + 1, 'R')
                        addEdge(i, j, 'L', i, j - 1, 'L')
                        addEdge(i, j, 'U', i, j + 1, 'R')
                        addEdge(i, j, 'U', i, j - 1, 'L')
                    }
                    else -> throw Exception("Unknown symbol")
                }
            }
        }
        dfs(id(0, 0, 'R'))
        var ans = 0
        for(i in input.indices) {
            for(j in input[0].indices) {
                for(dir in listOf('R', 'D', 'L', 'U')) {
                    if(used[id(i, j, dir)]) {
                        ans++
                        break
                    }
                }
            }
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        part1(input)
        var ans = 0
        for(i in input.indices) {
            for(j in input[0].indices) {
                for(dDir in listOf('R', 'D', 'L', 'U')) {
                    if(i == 0 && dDir == 'D')
                        dfs(id(i, j, dDir))
                    else if(i + 1 == n && dDir == 'U')
                        dfs(id(i, j, dDir))
                    else if(j == 0 && dDir == 'R')
                        dfs(id(i, j, dDir))
                    else if(j + 1 == m && dDir == 'L')
                        dfs(id(i, j, dDir))
                    else
                        continue
                    var cAns = 0
                    for (x in input.indices) {
                        for (y in input[0].indices) {
                            for (dir in listOf('R', 'D', 'L', 'U')) {
                                if (used[id(x, y, dir)]) {
                                    cAns++
                                    break
                                }
                            }
                        }
                    }
                    ans = max(ans, cAns)
                }
            }
        }
        return ans
    }

    val testInput1 = readInput("Day16_example1")
    check (part1(testInput1) == 46)

    val testInput2 = readInput("Day16_example2")
    check(part2(testInput2) == 51)

    val input = readInput("Day16")
    part1(input).println()
    part2(input).println()
}
