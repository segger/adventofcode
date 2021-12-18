package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.Integer.*
import java.lang.Math.abs
import java.util.*

object Day9 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(9, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var row = 0
        var maxCol = 0
        val heightmap = mutableMapOf<Pair<Int, Int>, Int>()
        while (scanner.hasNextLine()) {
            var col = 0
            scanner.nextLine().trim().chunked(1).forEach { s ->
                heightmap[Pair(row, col++)] = s.toInt()
            }
            row++
            maxCol = col
        }

        var lowPointSum = 0
        for (r in 0 until row) {
            for (c in 0 until maxCol) {
                val point = heightmap[Pair(r, c)]!!
                // println("check point at $r, $c value $point")
                val up = heightmap[Pair(r-1, c)]
                val right = heightmap[Pair(r, c+1)]
                val down = heightmap[Pair(r+1, c)]
                val left = heightmap[Pair(r, c-1)]
                if (lowpoint(point, up) && lowpoint(point, right) && lowpoint(point, down) && lowpoint(point, left)) {
                    lowPointSum += point + 1
                }
            }
        }

        println("lowpointsum $lowPointSum")
    }

    private fun lowpoint(point: Int, neighbor: Int?): Boolean {
        neighbor?.let {
            return point < it
        }
        return true
    }

    private fun star2(scanner: Scanner) {
        var row = 0
        var maxCol = 0
        val heightmap = mutableMapOf<Pair<Int, Int>, Int>()
        while (scanner.hasNextLine()) {
            var col = 0
            scanner.nextLine().trim().chunked(1).forEach { s ->
                heightmap[Pair(row, col++)] = s.toInt()
            }
            row++
            maxCol = col
        }

        val lowpoints = mutableMapOf<Pair<Int, Int>, Int>()
        for (r in 0 until row) {
            for (c in 0 until maxCol) {
                val point = heightmap[Pair(r, c)]!!
                // println("check point at $r, $c value $point")
                val up = heightmap[Pair(r-1, c)]
                val right = heightmap[Pair(r, c+1)]
                val down = heightmap[Pair(r+1, c)]
                val left = heightmap[Pair(r, c-1)]
                if (lowpoint(point, up) && lowpoint(point, right) && lowpoint(point, down) && lowpoint(point, left)) {
                    lowpoints[Pair(r, c)] = point
                }
            }
        }

        // println("lowpoints ${lowpoints.size}")

        /*
        heightmap.forEach {
            println("${it.key.first}, ${it.key.second} = ${it.value}")
        } */

        val basins = mutableListOf<Int>()
        for (lowpoint in lowpoints) {
            val basinSize = count(lowpoint.key, lowpoint.value, heightmap)
            basins.add(basinSize)
        }

        val sortedBassins = basins.sortedDescending()
        val result = sortedBassins[0] * sortedBassins[1] * sortedBassins[2]
        println("RESULT $result")
    }

    data class Point(val row: Int, val col: Int, val value: Int)

    private fun next(visited: MutableSet<Pair<Int, Int>>, point: Point, heightMap: Map<Pair<Int, Int>, Int>): MutableSet<Pair<Int, Int>> {
        // println("next ${point.col}, ${point.row} ${point.value}")
        visited.add(Pair(point.col, point.row))

        val leftPos = Pair(point.col - 1, point.row)
        val left = heightMap[leftPos]
        val upPos = Pair(point.col, point.row - 1)
        val up = heightMap[upPos]
        val rightPos = Pair(point.col + 1, point.row)
        val right = heightMap[rightPos]
        val downPos = Pair(point.col, point.row + 1)
        val down = heightMap[downPos]

        val check = mutableListOf<Point>()
        if (up != null && up != 9 && up > point.value && !visited.contains(upPos)) {
            check.add(Point(upPos.second, upPos.first, up))
        }
        if (right != null && right != 9 && right > point.value && !visited.contains(rightPos)) {
            check.add(Point(rightPos.second, rightPos.first, right))
        }
        if (down != null && down != 9 && down > point.value && !visited.contains(downPos)) {
            check.add(Point(downPos.second, downPos.first, down))
        }
        if (left != null && left != 9 && left > point.value && !visited.contains(leftPos)) {
            check.add(Point(leftPos.second, leftPos.first, left))
        }

        if (check.size == 0) {
            return visited
        } else {
            val sumVisited = mutableSetOf<Pair<Int, Int>>()
            check.forEach {
                sumVisited.addAll(next(visited, it, heightMap))
            }
            // println("sumVisited $sumVisited")
            return sumVisited
        }
    }

    private fun count(lowpoint: Pair<Int, Int>, lowValue: Int, heightMap: Map<Pair<Int, Int>, Int>): Int {
        val point = Point(lowpoint.second, lowpoint.first, lowValue)
        // println("LOWPOINT ${point.col}, ${point.row}, ${point.value}")
        val visited = mutableSetOf<Pair<Int, Int>>()
        visited.add(Pair(point.col, point.row))

        val total = next(visited, point, heightMap)
        // println("TOTAL ${total.size}")
        return total.size
    }
}