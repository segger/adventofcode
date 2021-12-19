package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.StringBuilder
import java.util.*

/**
 * 10 /36 paths
 * start-A
start-b
A-c
A-b
b-d
A-end
b-end
 *
 * 19 /103 paths
 * dc-end
HN-start
start-kj
dc-start
dc-HN
LN-dc
HN-end
kj-sa
kj-HN
kj-dc

226 /3509 paths
fs-end
he-DX
fs-he
start-DX
pj-DX
end-zg
zg-sl
zg-pj
pj-he
RW-he
fs-DX
pj-RW
zg-RW
start-pj
he-WI
zg-he
pj-fs
start-RW

 */
object Day12 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(12, false)

        // star1(scanner)
        star2(scanner)
    }

    class CaveGraph(val caves: Map<String, Int>) {
        val vertices = Array(caves.size){ mutableListOf<String>() }

        fun addEdge(from: String, to: String) {
            val fromIdx = caves[from]!!
            // println("from $from, fromIdx: $fromIdx - $to")
            vertices[fromIdx].add(to)

            val toIdx = caves[to]!!
            vertices[toIdx].add(from)
        }

        fun printGraph() {
            vertices.forEachIndexed { index, mutableList ->
                println("$index : $mutableList")
            }
        }

        fun star2(from: String, to: String) {
            val pathList = mutableListOf<String>()
            pathList.add(from)
            val visited = BooleanArray(caves.size)
            val totalPaths = printRecursive2(from, to, visited, pathList)
            println(totalPaths)
        }

        fun star1(from: String, to: String) {
            val pathList = mutableListOf<String>()
            pathList.add(from)
            val visited = BooleanArray(caves.size)
            val totalPaths = printRecursive(from, to, visited, pathList)
            println(totalPaths)
        }

        fun isUppercase(str: String): Boolean {
            val uppercase = str.toCharArray().map {
                it.isLowerCase()
            }.any { b -> !b }
            return uppercase
        }
        fun pathListContains(next: String, pathList: MutableList<String>): Boolean {
            if (isUppercase(next)) return false

            var hasDouble = false
            var hasValue = false
            pathList.groupBy { it }.forEach {
                if (!isUppercase(it.key)) {
                    if (it.value.count() > 1) {
                        hasDouble = true
                    }
                    if (it.key == next) {
                        hasValue = true
                    }
                }
            }

            if (hasDouble && hasValue) {
                return true
            } else if (hasValue) {
                return next == "start" || next == "end"
            } else {
                return false
            }
        }

        fun printRecursive2(current: String, to: String, visited: BooleanArray, pathList: MutableList<String>): Int {
            if (current == to) {
                // println("=== PATH ===")
                // pathList.forEach { print("$it - ") }
                // println()
                return 1
            }

            var sum = 0

            val idx = caves[current]!!

            for (next in vertices[idx]) {
                val nextIdx = caves[next]!!
                // println("next $next")
                if (!pathListContains(next, pathList)) {
                    // println("going from $current")
                    pathList.add(next)
                    sum += printRecursive2(next, to, visited, pathList)

                    // val lastIdxOf = pathList.lastIndexOf(next)
                    val lastIdx = pathList.lastIndex
                    pathList.removeAt(lastIdx)
                }
            }

            // visited[idx] = false

            return sum
        }

        fun printRecursive(current: String, to: String, visited: BooleanArray, pathList: MutableList<String>): Int {
            if (current == to) {
                // println("=== PATH ===")
                // pathList.forEach { print("$it - ") }
                // println()
                return 1
            }

            var sum = 0
            val uppercase = current.toCharArray().map {
                it.isLowerCase()
            }.any { b -> !b }
            val idx = caves[current]!!

            // println("current $current")
            // println("pathList.size ${pathList.size}")
            // println("is uppercase $uppercase")

            visited[idx] = !uppercase

            /*
            visited.forEachIndexed { index, b ->
                println("$index : $b")
            }*/

            // println("children of $current size ${vertices[idx].size}")
            for (next in vertices[idx]) {
                val nextIdx = caves[next]!!
                // println("next $next")
                if (!visited[nextIdx]) {
                    // println("going from $current")
                    pathList.add(next)
                    sum += printRecursive(next, to, visited, pathList)

                    // val lastIdxOf = pathList.lastIndexOf(next)
                    val lastIdx = pathList.lastIndex
                    pathList.removeAt(lastIdx)
                }
            }

            visited[idx] = false

            return sum
        }
    }

    private fun star1(scanner: Scanner) {
        val caveGraph = star(scanner)
        caveGraph.star1("start", "end")
        // caveGraph.printGraph()
    }

    private fun star2(scanner: Scanner) {
        val caveGraph = star(scanner)
        caveGraph.star2("start","end")
    }

    private fun star(scanner: Scanner): CaveGraph {
        val caves = mutableSetOf<String>()
        val edges = mutableListOf<Pair<String, String>>()
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine().split("-")
            val first = input[0]
            val second = input[1]
            caves.add(first)
            caves.add(second)
            edges.add(Pair(first, second))
        }
        val caveMap = mutableMapOf<String, Int>()
        caves.forEachIndexed { idx, cave ->
            // println("idx $idx, cave $cave")
            caveMap[cave] = idx
        }
        val caveGraph = CaveGraph(caveMap)
        for (edge in edges) {
            caveGraph.addEdge(edge.first, edge.second)
        }
        return caveGraph
    }
}