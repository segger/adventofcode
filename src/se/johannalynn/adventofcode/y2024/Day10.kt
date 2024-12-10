package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*
import kotlin.collections.LinkedHashSet
import kotlin.math.abs
import kotlin.math.pow

object Day10 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(10, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {

        var sum = 0
        var row = 0
        var maxCol = 0
        val map = mutableMapOf<Pair<Int, Int>, Point>()
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split("").filter { it.isNotBlank() }.map { it.toInt() }
            next.forEachIndexed { index, i ->
                map[Pair(index, row)] = Point(i, Pair(index, row), mutableListOf(), mutableListOf())
                if (index > maxCol) {
                    maxCol = index
                }
            }
            row++
        }
        val maxRow = row-1

        // println("maxRow: $maxRow, maxCol: $maxCol")
        val zeroPosition = mutableListOf<Pair<Int, Int>>()
        map.forEach { (key, value) ->
            val left = map[Pair(key.first-1, key.second)]
            val right = map[Pair(key.first+1, key.second)]
            val up = map[Pair(key.first, key.second-1)]
            val down = map[Pair(key.first, key.second+1)]

            link(left, value)
            link(right, value)
            link(up, value)
            link(down, value)

            if (value.value == 0) {
                zeroPosition.add(key)
            }
        }


        zeroPosition.forEach { (x, y) ->
            // println("zeroPosition: $x, $y")
            val score = mutableSetOf<Pair<Int, Int>>()
            map[Pair(x, y)]!!.froms.forEach { pos ->
                traverse(map, pos, score)
            }
            sum += score.size
        }

        println(sum)
    }

    private fun traverse(map: MutableMap<Pair<Int, Int>, Point>, pos: Pair<Int, Int>, score: MutableSet<Pair<Int, Int>>) {
        // println("pos: $pos, value: ${map[pos]!!.value}")
        if (map[pos]!!.value == 9) {
            score.add(pos)
        } else {
            map[pos]!!.froms.forEach { nextPos ->
                traverse(map, nextPos, score)
            }
        }
    }

    private fun link(trailPoint: Point?, currPoint: Point) {
        if (trailPoint != null) {
            when(trailPoint.value) {
                currPoint.value + 1 -> currPoint.froms.add(trailPoint.pos)
                currPoint.value - 1 -> currPoint.tos.add(trailPoint.pos)
            }
        }
    }

    data class Point(val value: Int, val pos: Pair<Int, Int>, val froms: MutableList<Pair<Int, Int>>, val tos: MutableList<Pair<Int, Int>>)

    private fun star2(scanner: Scanner) {

        var sum = 0
        var row = 0
        var maxCol = 0
        val map = mutableMapOf<Pair<Int, Int>, Point>()
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split("").filter { it.isNotBlank() }.map { it.toInt() }
            next.forEachIndexed { index, i ->
                map[Pair(index, row)] = Point(i, Pair(index, row), mutableListOf(), mutableListOf())
                if (index > maxCol) {
                    maxCol = index
                }
            }
            row++
        }
        val maxRow = row-1

        // println("maxRow: $maxRow, maxCol: $maxCol")
        val zeroPosition = mutableListOf<Pair<Int, Int>>()
        map.forEach { (key, value) ->
            val left = map[Pair(key.first-1, key.second)]
            val right = map[Pair(key.first+1, key.second)]
            val up = map[Pair(key.first, key.second-1)]
            val down = map[Pair(key.first, key.second+1)]

            link(left, value)
            link(right, value)
            link(up, value)
            link(down, value)

            if (value.value == 0) {
                zeroPosition.add(key)
            }
        }


        zeroPosition.forEach { (x, y) ->
            // println("zeroPosition: $x, $y")
            val rating = mutableListOf<Pair<Int, Int>>()
            map[Pair(x, y)]!!.froms.forEach { pos ->
                traverse2(map, pos, rating)
            }
            sum += rating.size
        }

        println(sum)
    }

    private fun traverse2(map: MutableMap<Pair<Int, Int>, Point>, pos: Pair<Int, Int>, rating: MutableList<Pair<Int, Int>>) {
        // println("pos: $pos, value: ${map[pos]!!.value}")
        if (map[pos]!!.value == 9) {
            // println("trail")
            rating.add(pos)
        } else {
            map[pos]!!.froms.forEach { nextPos ->
                traverse2(map, nextPos, rating)
            }
        }
    }
}
