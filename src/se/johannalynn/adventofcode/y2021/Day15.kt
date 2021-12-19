package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.Integer.*
import java.lang.Math.abs
import java.util.*

object Day15 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(15, false)

        // star1(scanner)
        star2(scanner)
    }

    class Edge(val v1: String, val v2: String, val dist: Int)

    class Vertex(val name: String) : Comparable<Vertex> {
        var dist = Int.MAX_VALUE
        var prev: Vertex? = null
        val neighbors = mutableMapOf<Vertex, Int>()

        fun printPath() {
            if (this == prev) {
                print(name)
            } else if (prev == null) {
                print("$name(unreached)")
            } else {
                prev!!.printPath()
                print(" -> $name($dist)")
            }
        }

        fun printDist() {
            println("dist $dist")
        }

        override fun compareTo(other: Vertex): Int {
            if (dist == other.dist) return name.compareTo(other.name)
            return dist.compareTo(other.dist)
        }

        override fun toString() = "($name,$dist)"
    }

    class MatrixGraph(private val edges: List<Edge>) {
        private val graph = HashMap<String, Vertex>(edges.size)

        init {
            for (e in edges) {
                if (!graph.contains(e.v1)) graph[e.v1] = Vertex(e.v1)
                if (!graph.contains(e.v2)) graph[e.v2] = Vertex(e.v2)
            }

            for (e in edges) {
                graph[e.v1]!!.neighbors[graph[e.v2]!!] = e.dist
            }
        }

        fun dijkstra(start: String) {
            val source = graph[start]
            val q = TreeSet<Vertex>()
            for (v in graph.values) {
                v.prev = if (v == source) source else null
                v.dist = if (v == source) 0 else Int.MAX_VALUE
                q.add(v)
            }
            dijkstra(q)
        }

        private fun dijkstra(q: TreeSet<Vertex>) {
            while(!q.isEmpty()) {
                val vert = q.pollFirst()
                // println("vert $vert")
                if (vert.dist == Int.MAX_VALUE) break
                for (n in vert.neighbors) {
                    val k = n.key

                    val altDist = vert.dist + n.value
                    if (altDist < k.dist) {
                        q.remove(k)
                        k.dist = altDist
                        k.prev = vert
                        q.add(k)
                    }
                }
            }
        }

        fun printPath(stop: String) {
            graph[stop]!!.printPath()
        }

        fun printDist(stop: String) {
            graph[stop]!!.printDist()
        }
    }

    private fun star2(scanner: Scanner) {
        val matrix = mutableMapOf<Pair<Int, Int>, Int>()
        var maxCol = 0
        var row = 0
        while (scanner.hasNextLine()) {
            scanner.nextLine().chunked(1).forEachIndexed { idx, it ->
                matrix[Pair(row, idx)] = it.toInt()
                if (idx > maxCol) {
                    maxCol = idx
                }
            }
            row++
        }

        val matrix3 = mutableMapOf<Pair<Int, Int>, Int>()
        val matrix2 = Array(5*row ){ IntArray(5*(maxCol+1))}

        for (r in 0 until row) {
            for (c in 0..maxCol) {
                for(i in 0..4) {
                    for (j in 0..4) {
                        val rr = r+(i*(row))
                        val cc = c+(j*(maxCol+1))

                        val currentPair = Pair(r, c)
                        val currentValue = matrix[currentPair]!!

                        // val currentName = pairName(currentPair)
                        var dist = (currentValue+(i)+(j))
                        if (dist >= 10) {
                            dist -= 9
                        }

                        matrix2[rr][cc] =  dist
                        matrix3[Pair(rr, cc)] = dist
                    }
                }
                // print(matrix[Pair(r, c)])
            }
            // println()
        }

        /*
        for (r3 in 0..matrix2.size-1) {
            for (c3 in 0..matrix2[r3].size-1) {
                // print("$r3,$c3: ${matrix2[r3][c3]}")
                print("${matrix2[r3][c3]}")
            }
            println()
        } */

        val edges = mutableListOf<Edge>()
        for (r2 in 0..matrix2.size-1) {
            for (c2 in 0..matrix2[r2].size-1) {
                val currentPair = Pair(r2, c2)
                // val currentValue = matrix[currentPair]!!
                val currentName = pairName(currentPair)

                val top = Pair(r2-1, c2)
                val topValue = matrix3[top]
                val right = Pair(r2, c2+1)
                val rightValue = matrix3[right]
                val down = Pair(r2+1, c2)
                val downValue = matrix3[down]
                val left = Pair(r2, c2-1)
                val leftValue = matrix3[left]

                topValue?.let {
                    edges.add(Edge(currentName, pairName(top), it))
                }
                rightValue?.let {
                    edges.add(Edge(currentName, pairName(right), it))
                }
                downValue?.let {
                    edges.add(Edge(currentName, pairName(down), it))
                }
                leftValue?.let {
                    edges.add(Edge(currentName, pairName(left), it))
                }
            }
        }

        with (MatrixGraph(edges)) {
            dijkstra(pairName(Pair(0,0)))
            // printDist(pairName(Pair(49, 49)))
            // printDist(pairName(Pair(9, 9)))
            // println("${matrix2.size} - ${matrix2[0].size}")
            printDist(pairName(Pair(matrix2.size-1, matrix2[0].size-1)))
        }
    }

    private fun star2x(scanner: Scanner) {
        val matrix = mutableMapOf<Pair<Int, Int>, Int>()
        var maxCol = 0
        var row = 0
        while (scanner.hasNextLine()) {
            scanner.nextLine().chunked(1).forEachIndexed { idx, it ->
                matrix[Pair(row, idx)] = it.toInt()
                if (idx > maxCol) {
                    maxCol = idx
                }
            }
            row++
        }

        val edges = mutableListOf<Edge>()
        // val points = mutableListOf<String>()
        val points = mutableSetOf<String>()
        for (r in 0 until row) {
            for (c in 0..maxCol) {
                for(i in 0..4) {
                    for (j in 0..4) {
                        val rr = r+(i*(row))
                        val cc = c+(j*(maxCol+1))

                        val currentPair = Pair(rr, cc)
                        val currentName = pairName(currentPair)
                        // points.add(currentName)

                        val top = Pair(rr-1, cc)
                        val topValue = matrix[Pair(r-1, c)]
                        val right = Pair(rr, cc+1)
                        val rightValue = matrix[Pair(r, c+1)]
                        val down = Pair(rr+1, cc)
                        val downValue = matrix[Pair(r+1, c)]
                        val left = Pair(rr, cc-1)
                        val leftValue = matrix[Pair(r, c-1)]

                        topValue?.let {
                            var dist = (it+i+j) % 10
                            if (dist == 0) dist = 1
                            edges.add(Edge(currentName, pairName(top), dist))
                        }
                        rightValue?.let {
                            var dist = (it+i+j) % 10
                            if (dist == 0) dist = 1
                            edges.add(Edge(currentName, pairName(right), dist))
                        }
                        downValue?.let {
                            var dist = (it+i+j) % 10
                            if (dist == 0) dist = 1
                            edges.add(Edge(currentName, pairName(down), dist))
                        }
                        leftValue?.let {
                            var dist = (it+i+j) % 10
                            if (dist == 0) dist = 1
                            edges.add(Edge(currentName, pairName(left), dist))
                        }

                    }
                }
                // print(matrix[Pair(r, c)])
            }
            // println()
        }

        println("${edges.size}")
        edges.forEach {
            points.add("${it.v1}")
            points.add("${it.v2}")
            // println("${it.v1} ${it.v2} : ${it.dist}")
        }
        // println("${points.size}")

        with (MatrixGraph(edges)) {
            dijkstra(pairName(Pair(0,0)))
            // printDist(pairName(Pair((row-1)+25, maxCol+25)))
            // printDist(pairName(Pair(49, 49)))
            printDist(pairName(Pair(9, 9)))
            printDist(pairName(Pair(9, 10)))
            // printDist(pairName(Pair(row-1, maxCol)))
        }
    }

    private fun star1(scanner: Scanner) {
        val matrix = mutableMapOf<Pair<Int, Int>, Int>()
        var maxCol = 0
        var row = 0
        while (scanner.hasNextLine()) {
            scanner.nextLine().chunked(1).forEachIndexed { idx, it ->
                matrix[Pair(row, idx)] = it.toInt()
                if (idx > maxCol) {
                    maxCol = idx
                }
            }
            row++
        }

        val edges = mutableListOf<Edge>()
        for (r in 0 until row) {
            for (c in 0..maxCol) {
                val currentPair = Pair(r, c)
                val currentName = pairName(currentPair)

                val top = Pair(r-1, c)
                val topValue = matrix[Pair(r-1, c)]
                val right = Pair(r, c+1)
                val rightValue = matrix[Pair(r, c+1)]
                val down = Pair(r+1, c)
                val downValue = matrix[Pair(r+1, c)]
                val left = Pair(r, c-1)
                val leftValue = matrix[Pair(r, c-1)]

                topValue?.let {
                    edges.add(Edge(currentName, pairName(top), it))
                }
                rightValue?.let {
                    edges.add(Edge(currentName, pairName(right), it))
                }
                downValue?.let {
                    edges.add(Edge(currentName, pairName(down), it))
                }
                leftValue?.let {
                    edges.add(Edge(currentName, pairName(left), it))
                }
                // print(matrix[Pair(r, c)])
            }
            // println()
        }

        with (MatrixGraph(edges)) {
            dijkstra(pairName(Pair(0,0)))
            printDist(pairName(Pair(row-1, maxCol)))
        }
    }

    private fun pairName(pair: Pair<Int, Int>): String {
        val row = "${pair.first}".padStart(2, '0')
        val col = "${pair.second}".padStart(2, '0')
        return "($row,$col)"
    }
}