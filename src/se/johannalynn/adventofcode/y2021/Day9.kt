package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.Integer.*
import java.lang.Math.abs
import java.util.*

object Day9 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(9, true)

        star1(scanner)
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
}