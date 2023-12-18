fun main() {
    fun hash(str: String): Int {
        var ans = 0
        for(c in str)
            ans = (ans + c.code) * 17 % 256
        return ans
    }

    fun part1(input: List<String>): Int {
        val str = input.joinToString { it }
        val hashes = str.split(",", " ").filter { it != "" }.map { hash(it) }
        return hashes.sum()
    }

    val boxes = MutableList(256) { mutableListOf<Pair<String, Int>>() }
    fun part2(input: List<String>): Int {
        boxes.forEach { it.clear() }
        val str = input.joinToString { it }
        str.split(",", " ").filter { it != "" }.forEach {
            if('-' in it) {
                val trimmedString = it.dropLast(1)
                val hsh = hash(trimmedString)
                boxes[hsh].removeIf { x -> x.first == trimmedString }
            } else {
                val trimmedString = it.dropLast(2)
                val hsh = hash(trimmedString)
                val lens = it.last().digitToInt()
                if(boxes[hsh].none { x -> x.first == trimmedString })
                    boxes[hsh].add(Pair(trimmedString, lens))
                else {
                    val index = boxes[hsh].indexOfFirst { x -> x.first == trimmedString }
                    boxes[hsh][index] = Pair(trimmedString, lens)
                }
            }
        }
        var ans = 0
        boxes.forEachIndexed { boxIndex, box ->
            box.forEachIndexed { lensIndex, (_, lens) ->
                ans += (boxIndex + 1) * (lensIndex + 1) * lens
            }
        }
        return ans
    }

    val testInput1 = readInput("Day15_example1")
    check (part1(testInput1) == 1320)

    val testInput2 = readInput("Day15_example2")
    check(part2(testInput2) == 145)

    val input = readInput("Day15")
    part1(input).println()
    part2(input).println()
}
