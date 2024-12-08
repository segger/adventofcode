package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*
import kotlin.collections.LinkedHashSet
import kotlin.math.abs
import kotlin.math.pow

object Day8 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(8, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {

        var antinodes = mutableSetOf<Pair<Int, Int>>()
        // val antennas = mutableListOf<Pair<String, Pair<Int, Int>>>()
        val antennas = mutableMapOf<String, List<Pair<Int, Int>>>()
        var row = 0
        var colMax = 0
        while (scanner.hasNextLine()) {
            scanner.nextLine().split("").filter { it.isNotBlank() }.forEachIndexed { idx, s ->
                if (s != ".") {
                    val positions = if (antennas[s] != null) {
                        antennas[s]!!.toMutableList()
                    } else {
                        mutableListOf()
                    }
                    positions.add(Pair(idx, row))
                    antennas[s] = positions
                }
                if (idx > colMax) {
                    colMax = idx
                }
            }
            row++
        }

        val rowMax = row-1
        println("colMax: $colMax")
        println("rowMax: $rowMax")

        antennas.forEach { t, u ->
            // println("T $t ${u.size}")
            if (u.size > 1) {
                val combinations = u.mapIndexed { idx, item1 ->
                    u.slice(idx +1 until u.size).map { item2 ->
                        Pair(item1, item2)
                    }
                }.flatten()

                combinations.forEach {
                    // println("$it")
                    val diffX = abs(it.first.first - it.second.first)
                    val diffY = abs(it.first.second - it.second.second)

                    // println("$diffX $diffY")

                    val x = if (it.first.first > it.second.first) {
                        Pair(it.first.first + diffX, it.second.first - diffX)
                    } else {
                        Pair(it.first.first - diffX, it.second.first + diffX)
                    }
                    val y = if (it.first.second > it.second.second) {
                        Pair(it.first.second + diffY, it.second.second - diffY)
                    } else {
                        Pair(it.first.second - diffY, it.second.second + diffY)
                    }

                    // println("$x $y")

                    if (x.first in 0..colMax && y.first in 0..rowMax) {
                        antinodes.add(Pair(x.first, y.first))
                        // println("${x.first}, ${y.first}")
                    }
                    if (x.second in 0..colMax && y.second in 0..rowMax) {
                        antinodes.add(Pair(x.second, y.second))
                        // println("${x.second}, ${y.second}")
                    }
                }
            }
        }

        println(antinodes.size)
        // 263 too high
    }

    private fun star2(scanner: Scanner) {

        var antinodes = mutableSetOf<Pair<Int, Int>>()
        // val antennas = mutableListOf<Pair<String, Pair<Int, Int>>>()
        val antennas = mutableMapOf<String, List<Pair<Int, Int>>>()
        var row = 0
        var colMax = 0
        while (scanner.hasNextLine()) {
            scanner.nextLine().split("").filter { it.isNotBlank() }.forEachIndexed { idx, s ->
                if (s != ".") {
                    val positions = if (antennas[s] != null) {
                        antennas[s]!!.toMutableList()
                    } else {
                        mutableListOf()
                    }
                    positions.add(Pair(idx, row))
                    antennas[s] = positions
                }
                if (idx > colMax) {
                    colMax = idx
                }
            }
            row++
        }

        val rowMax = row-1

        antennas.forEach { t, u ->
            // println("T $t ${u.size}")
            if (u.size > 1) {
                val combinations = u.mapIndexed { idx, item1 ->
                    u.slice(idx +1 until u.size).map { item2 ->
                        Pair(item1, item2)
                    }
                }.flatten()

                combinations.forEach {
                    // println("$it")
                    val diffX = it.first.first - it.second.first
                    val diffY = it.first.second - it.second.second

                    var x1 = it.first.first
                    var y1 = it.first.second

                    var x2 = it.first.first
                    var y2 = it.first.second

                    while (x1 in 0..colMax && y1 in 0..rowMax) {
                        antinodes.add(Pair(x1, y1))
                        x1 += diffX
                        y1 += diffY
                    }
                    while (x2 in 0..colMax && y2 in 0..rowMax) {
                        antinodes.add(Pair(x2, y2))
                        x2 -= diffX
                        y2 -= diffY
                    }
                }
            }
        }

        println(antinodes.size)
        // 263 too high
    }
}
