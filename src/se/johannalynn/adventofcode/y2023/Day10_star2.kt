package se.johannalynn.adventofcode.y2023

import java.lang.System.exit
import java.util.*
import kotlin.math.abs

/**
 * ..F7.
 * .FJ|.
 * SJ.L7
 * |F--J
 * LJ...
 *
 * ..........
 * .S------7.
 * .|F----7|.
 * .||....||.
 * .||....||.
 * .|L-7F-J|.
 * .|..||..|.
 * .L--JL--J.
 * ..........
 *
 * .F----7F7F7F7F-7....
 * .|F--7||||||||FJ....
 * .||.FJ||||||||L7....
 * FJL7L7LJLJ||LJ.L-7..
 * L--J.L7...LJS7F-7L7.
 * ....F-J..F7FJ|L7L7L7
 * ....L7.F7||L7|.L7L7|
 * .....|FJLJ|FJ|F7|.LJ
 * ....FJL-7.||.||||...
 * ....L---J.LJ.LJLJ...
 *
 * 509 too high
 */
object Day10_star2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(10, false)

        star2(scanner)
    }

    enum class Direction {
        LEFT, RIGHT, UP, DOWN
    }

    data class Tile(val x: Int, val y: Int, val pipe: String) {
        var from: Direction? = null
    }

    private fun star2(scanner: Scanner) {
        val ground = mutableMapOf<Pair<Int,Int>, Tile>()
        var row = 0
        var start = Pair(0, 0)
        while (scanner.hasNextLine()) {
            scanner.nextLine().split("").filter { it.isNotEmpty() }.forEachIndexed { col, tile ->
                ground[Pair(row, col)] = Tile(col, row, tile)
                if (tile == "S") {
                    start = Pair(row, col)
                }
            }
            row++
        }

        val first = startCoords(ground, start)
        // loop(ground, first)

        val startPoint = start
        var currPoint = first
        var vertices = mutableListOf<Pair<Int, Int>>()
        vertices.add(startPoint)

        var length = 1
        while (startPoint.first != currPoint.first || startPoint.second != currPoint.second) {
            val tile = ground[currPoint]
            if (arrayOf("F","7","L","J").contains(tile!!.pipe)) {
                vertices.add(currPoint)
            }
            currPoint = next(ground, currPoint)
            length++
        }

        val area = shoelace(vertices)
        println("shoelace $area")
        val enclosed = picks_formula(area, length)
        println(enclosed)
    }

    private fun shoelace(vertices: List<Pair<Int, Int>>): Int {
        val length = vertices.size
        var sum1 = 0
        var sum2 = 0

        for (i in 0 until length - 1) {
            sum1 += vertices[i].first * vertices[i + 1].second
            sum2 += vertices[i].second * vertices[i + 1].first
        }

        sum1 += vertices[length - 1].first * vertices[0].second
        sum2 += vertices[length - 1].second * vertices[0].first

        return abs(sum1 - sum2) / 2
    }

    private fun picks_formula(area: Int, length: Int): Int {
        return area - (length/2) + 1
    }

    private fun startCoords(ground: MutableMap<Pair<Int, Int>, Tile>, start: Pair<Int, Int>): Pair<Int, Int> {
        return if (arrayOf("-", "L", "F").contains(ground[Pair(start.first, start.second - 1)]!!.pipe)) {
            val coords = Pair(start.first, start.second - 1)
            ground[coords]!!.from = Direction.RIGHT
            coords
        } else if (arrayOf("|", "F", "J").contains(ground[Pair(start.first - 1, start.second)]!!.pipe)) {
            val coords = Pair(start.first - 1, start.second)
            ground[coords]!!.from = Direction.DOWN
            coords
        } else if (arrayOf("-", "J", "7").contains(ground[Pair(start.first, start.second + 1)]!!.pipe)) {
            val coords = Pair(start.first, start.second + 1)
            ground[coords]!!.from = Direction.LEFT
            coords
        } else if (arrayOf("|", "L", "J").contains(ground[Pair(start.first + 1, start.second)]!!.pipe)) {
            val coords = Pair(start.first + 1, start.second)
            ground[coords]!!.from = Direction.UP
            coords
        } else {
            println("whut")
            exit(1)
            Pair(0,0)
        }
    }

    private fun next(ground: MutableMap<Pair<Int, Int>, Tile>, loop: Pair<Int, Int>): Pair<Int, Int> {
        val tile = ground[loop]!!
        // println("${tile.pipe} ${tile.distance} ${tile.from}")
        val nextKey = when(tile.pipe) {
            "-" -> {
                if (tile.from == Direction.LEFT) {
                    val next = Pair(loop.first, loop.second + 1)
                    ground[next]!!.from = Direction.LEFT
                    next
                } else {
                    val next = Pair(loop.first, loop.second - 1)
                    ground[next]!!.from = Direction.RIGHT
                    next
                }
            }
            "|" -> {
                if (tile.from == Direction.UP) {
                    val next = Pair(loop.first + 1, loop.second)
                    ground[next]!!.from = Direction.UP
                    next
                } else {
                    val next = Pair(loop.first - 1, loop.second)
                    ground[next]!!.from = Direction.DOWN
                    next
                }
            }
            "L" -> {
                if (tile.from == Direction.RIGHT) {
                    val next = Pair(loop.first - 1, loop.second)
                    ground[next]!!.from = Direction.DOWN
                    next
                } else {
                    val next = Pair(loop.first, loop.second + 1)
                    ground[next]!!.from = Direction.LEFT
                    next
                }
            }
            "J" -> {
                if (tile.from == Direction.LEFT) {
                    val next = Pair(loop.first - 1, loop.second)
                    ground[next]!!.from = Direction.DOWN
                    next
                } else {
                    val next = Pair(loop.first, loop.second - 1)
                    ground[next]!!.from = Direction.RIGHT
                    next
                }
            }
            "F" -> {
                if (tile.from == Direction.DOWN) {
                    val next = Pair(loop.first, loop.second + 1)
                    ground[next]!!.from = Direction.LEFT
                    next
                } else {
                    val next = Pair(loop.first + 1, loop.second)
                    ground[next]!!.from = Direction.UP
                    next
                }
            }
            "7" -> {
                if (tile.from == Direction.DOWN) {
                    val next = Pair(loop.first, loop.second - 1)
                    ground[next]!!.from = Direction.RIGHT
                    next
                } else {
                    val next = Pair(loop.first + 1, loop.second)
                    ground[next]!!.from = Direction.UP
                    next
                }
            }
            else -> {
                println("whut")
                exit(1)
                loop
            }
        }
        return nextKey
    }
}