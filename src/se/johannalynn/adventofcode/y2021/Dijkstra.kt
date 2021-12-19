package se.johannalynn.adventofcode.y2021

import java.util.*
import kotlin.collections.HashMap

object Dijkstra {

    class Edge(val start: String, val stop: String, val dist: Int)
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
                print("-> $name($dist)")
            }
        }

        override fun compareTo(other: Vertex): Int {
            if (dist == other.dist) return name.compareTo(other.name)
            return dist.compareTo(other.dist)
        }

        override fun toString() = "($name,$dist)"
    }

    class Graph(val edges: List<Edge>, val directed: Boolean) {
        private val graph = HashMap<String, Vertex>(edges.size)

        init {
            for (e in edges) {
                if (!graph.contains(e.start)) graph[e.start] = Vertex(e.start)
                if (!graph.contains(e.stop)) graph[e.stop] = Vertex(e.stop)
            }

            for (e in edges) {
                graph[e.start]!!.neighbors[graph[e.stop]!!] = e.dist
                if (!directed) {
                    graph[e.stop]!!.neighbors[graph[e.start]!!] = e.dist
                }
            }
        }

        fun dijkstra(start: String) {
            if (!graph.contains(start)) {
                println("start non valid")
                return
            }
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
                val u = q.pollFirst()
                if (u.dist == Int.MAX_VALUE) break
                for (a in u.neighbors) {
                    val v = a.key

                    val altDist = u.dist + a.value
                    if (altDist < v.dist) {
                        q.remove(v)
                        v.dist = altDist
                        v.prev = u
                        q.add(v)
                    }
                }
            }
        }

        fun printPath(stop: String) {
            if (!graph.contains(stop)) {
                println("stop non valid")
                return
            }
            graph[stop]!!.printPath()
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = listOf(
            Edge("a", "b", 7),
            Edge("a", "c", 9),
            Edge("a", "f", 14),
            Edge("b", "c", 10),
            Edge("b", "d", 15),
            Edge("c", "d", 11),
            Edge("c", "f", 2),
            Edge("d", "e", 6),
            Edge("e", "f", 9)
        )

        val start = "a"
        val stop = "e"

        with (Graph(input, false)) {
            dijkstra(start)
            printPath(stop)
        }
    }
}