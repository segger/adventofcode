package se.johannalynn.adventofcode.y2023

import java.lang.System.exit
import java.util.*

/**
 * ..F7.
 * .FJ|.
 * SJ.L7
 * |F--J
 * LJ...
 */
object Day10 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(10, false)

        star1(scanner)
    }

    enum class Direction {
        LEFT, RIGHT, UP, DOWN
    }

    data class Tile(val x: Int, val y: Int, val pipe: String) {
        var distance: Int? = null
        var from: Direction? = null
    }

    private fun star1(scanner: Scanner) {
        val ground = mutableMapOf<Pair<Int, Int>, Tile>()
        var row = 0
        var start = Pair(0, 0)
        while(scanner.hasNextLine()) {
            scanner.nextLine().split("").forEachIndexed { col, tile ->
                ground[Pair(row, col)] = Tile(col, row, tile)
                if (tile == "S") {
                    start = Pair(row, col)
                }
            }
            row++
        }

        var distance = 0
        ground[start]!!.distance = distance

        println("start $start")

        val coords = if (arrayOf("-", "J", "7").contains(ground[Pair(start.first, start.second + 1)]!!.pipe)) {
            val coords = Pair(start.first, start.second + 1)
            val tile = ground[coords]!!
            tile.distance = ++distance
            tile.from = Direction.LEFT
            ground[coords] = tile
            println("right")
            coords
        } else if (arrayOf("|", "L", "J").contains(ground[Pair(start.first + 1, start.second)]!!.pipe)) {
            val coords = Pair(start.first + 1, start.second)
            val tile = ground[coords]!!
            tile.distance = ++distance
            tile.from = Direction.UP
            ground[coords] = tile
            println("down")
            coords
        } else if (arrayOf("-", "L", "F").contains(ground[Pair(start.first, start.second - 1)]!!.pipe)) {
            val coords = Pair(start.first, start.second - 1)
            val tile = ground[coords]!!
            tile.distance = ++distance
            tile.from = Direction.RIGHT
            ground[coords] = tile
            println("left")
            coords
        } else if (arrayOf("|", "F", "J").contains(ground[Pair(start.first - 1, start.second)]!!.pipe)) {
            val coords = Pair(start.first - 1, start.second)
            val tile = ground[coords]!!
            tile.distance = ++distance
            tile.from = Direction.DOWN
            ground[coords] = tile
            println("up")
            coords
        } else {
            println("whut")
            exit(1)
            Pair(0,0)
        }

        var loop = coords
        var c = 0
        while (c < 10000000000) {
            val tile = ground[loop]!!
            // println("${tile.pipe} ${tile.distance} ${tile.from}")
            when(tile.pipe) {
                "-" -> {
                    if (tile.from == Direction.LEFT) {
                        loop = Pair(loop.first, loop.second + 1)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.LEFT
                        ground[loop] = tmp
                    } else {
                        loop = Pair(loop.first, loop.second - 1)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.RIGHT
                        ground[loop] = tmp
                    }
                }
                "|" -> {
                    if (tile.from == Direction.UP) {
                        loop = Pair(loop.first + 1, loop.second)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.UP
                        ground[loop] = tmp
                    } else {
                        loop = Pair(loop.first - 1, loop.second)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.DOWN
                        ground[loop] = tmp
                    }
                }
                "L" -> {
                    if (tile.from == Direction.RIGHT) {
                        loop = Pair(loop.first - 1, loop.second)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.DOWN
                        ground[loop] = tmp
                    } else {
                        loop = Pair(loop.first, loop.second + 1)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.LEFT
                        ground[loop] = tmp
                    }
                }
                "J" -> {
                    if (tile.from == Direction.LEFT) {
                        loop = Pair(loop.first - 1, loop.second)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.DOWN
                        ground[loop] = tmp
                    } else {
                        loop = Pair(loop.first, loop.second - 1)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.RIGHT
                        ground[loop] = tmp
                    }
                }
                "F" -> {
                    if (tile.from == Direction.DOWN) {
                        loop = Pair(loop.first, loop.second + 1)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.LEFT
                        ground[loop] = tmp
                    } else {
                        loop = Pair(loop.first + 1, loop.second)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.UP
                        ground[loop] = tmp
                    }
                }
                "7" -> {
                    if (tile.from == Direction.DOWN) {
                        loop = Pair(loop.first, loop.second - 1)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.RIGHT
                        ground[loop] = tmp
                    } else {
                        loop = Pair(loop.first + 1, loop.second)
                        val tmp = ground[loop]!!
                        tmp.distance = ++distance
                        tmp.from = Direction.UP
                        ground[loop] = tmp
                    }
                }
                "S" -> {
                    break
                }
                else -> {
                    println("whut")
                    exit(1)
                }
            }
            c++
            // println(loop)
        }

        val totalDistance = ground.filter { it.value.distance != null }.maxOf { it.value.distance!! }
        if (totalDistance % 2 == 0) {
            println(totalDistance / 2)
        } else {
            println(totalDistance / 2 + 1)
        }
    }
}