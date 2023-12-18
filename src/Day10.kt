import java.util.LinkedList
import java.util.Stack

fun main() {
    var g = mutableListOf<Stack<Int>>()
    var n = 0
    var m = 0

    fun id(x: Int, y: Int) = x * m + y
    fun good(x: Int, y: Int) = x in 0..<n && y in 0..<m

    fun vertConnect(a: Char, b: Char): Boolean {
        val aIsGood = (a == '|' || a == '7' || a == 'F' || a == 'S')
        val bIsGood = (b == '|' || b == 'L' || b == 'J' || b == 'S')
        return aIsGood && bIsGood
    }

    fun horizConnect(a: Char, b: Char): Boolean {
        val aIsGood = (a == '-' || a == 'L' || a == 'F' || a == 'S')
        val bIsGood = (b == '-' || b == 'J' || b == '7' || b == 'S')
        return aIsGood && bIsGood
    }

    var s = 0
    var cycle: List<Int>
    fun dfs(v: Int) {
        val kek = Stack<Int>()
        kek.push(v)
        while (kek.isNotEmpty()) {
            if(g[kek.last()].isEmpty()) {
                kek.pop()
                continue
            }
            if(kek.last() == s && kek.size > 2) {
                cycle = kek.toList()
                return
            }
            val u = g[kek.last()].pop()
            if(kek.size > 1 && u == kek[kek.size - 2]) continue
            kek.push(u)
        }
    }

    fun buildGraph(input: List<String>){
        n = input.size
        m = input[0].length
        g = MutableList (n * m) { Stack() }
        for(i in input.indices) {
            for(j in input[0].indices) {
                if (good(i - 1, j) && vertConnect(input[i - 1][j], input[i][j])) {
                    g[id(i, j)].add(id(i - 1, j))
                    g[id(i - 1, j)].add(id(i, j))
                }
                if(good(i, j - 1) && horizConnect(input[i][j - 1], input[i][j])) {
                    g[id(i, j)].add(id(i, j - 1))
                    g[id(i, j - 1)].add(id(i, j))
                }
                if(input[i][j] == 'S') s = id(i, j)
            }
        }
    }

    fun part1(input: List<String>): Int {
        cycle = mutableListOf()
        buildGraph(input)
        dfs(s)
        return cycle.size / 2
    }

    fun adj(v: Int) : MutableList<Int> {
        val x = v / m
        val y = v % m
        val ans = mutableListOf<Int>()
        if(good(x - 1, y)) ans.add(id(x - 1, y))
        if(good(x + 1, y)) ans.add(id(x + 1, y))
        if(good(x, y - 1)) ans.add(id(x, y - 1))
        if(good(x, y + 1)) ans.add(id(x, y + 1))
        return ans
    }

    fun part2(input: List<String>): Int {
        cycle = mutableListOf()
        buildGraph(input)
        dfs(s)
        val isCycle = MutableList(n * m) { false }
        cycle.forEach { isCycle[it] = true  }
        var ans = 0
        for(i in 0..<n) {
            for(j in 0..<m) {
                if(input[i][j] == '.') {
                    ans++
                }
            }
        }
        val field = input.map { it.toMutableList() }.toMutableList()
        cycle.forEach { field[it / m][it % m] = 'C' }
        val used = MutableList(n * m) { false }
        val qu = LinkedList<Int>()
        for(j in 0..<m) {
            if(!isCycle[id(0, j)]) {
                qu.add(id(0, j))
                used[id(0, j)] = true
                if(input[0][j] == '.') {
                    ans--
                    field[0][j] = 'O'
                }
            }
            if(!isCycle[id(n - 1, j)]) {
                qu.add(id(n - 1, j))
                used[id(n - 1, j)] = true
                if(input[n - 1][j] == '.') {
                    ans--
                    field[n - 1][j] = 'O'
                }
            }
        }
        for(i in 0..<n) {
            if(!used[id(i, 0)] && !isCycle[id(i, 0)]) {
                qu.add(id(i, 0))
                used[id(i, 0)] = true
                if(input[i][0] == '.') {
                    ans--
                    field[i][0] = 'O'
                }
            }
            if(!used[id(i, m -1)] && !isCycle[id(i, m - 1)]) {
                qu.add(id(i, m - 1))
                used[id(i, m - 1)] = true
                if(input[i][m - 1] == '.') {
                    ans--
                    field[i][m - 1] = 'O'
                }
            }
        }
        while(qu.isNotEmpty()) {
            val v = qu.poll()
            for(u in adj(v)) {
                if(!used[u] && !isCycle[u]) {
                    used[u] = true
                    qu.add(u)
                    if(input[u / m][u % m] == '.') {
                        ans--
                        field[u / m][u % m] = 'O'
                    }
                }
            }
        }
        return ans
    }

    val testInput1 = readInput("Day10_example1")
    check (part1(testInput1) == 8)

//    val testInput2 = readInput("Day10_example2")
//    check(part2(testInput2) == 8)

    val input = readInput("Day10")
    part1(input).println()
//    part2(input).println()
}
