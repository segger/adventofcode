package se.johannalynn.adventofcode.y2023

import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Day11 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(11, false)

        // star1(scanner)
        star2(scanner, 1000000L)
    }

    private fun star1(scanner: Scanner, times: Long = 1) {
        val image = mutableMapOf<Pair<Int, Int>, Boolean>()

        val rowsWithoutGalaxies = mutableListOf<Int>()
        var row = 0
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine().split("")
            var galaxy = false
            input.forEachIndexed { index, s ->
                if (s == "#") {
                    image[Pair(index - 1, row)] = true
                    galaxy = true
                }
            }
            if (!galaxy) {
                // add a row
                rowsWithoutGalaxies.add(row)
            }
            row++
        }
        // colsWithoutGalaxies.forEach { println(it) }
        // println()

        // rows not containing galaxies
        val colsWithoutGalaxies = (0..(row - 1)).minus(image.keys.groupBy { it.first }.keys)

        // image.filter { it.value }.keys.sortedBy { it.first }.forEach { println(it) }
        var sum = 0L
        val galaxies = image.filter { it.value }.keys.sortedBy { it.second }
        for (i in 0 until galaxies.size) {
            val galaxy = galaxies[i]
            //println("galaxy: $galaxy")

            for(j in i+1 until galaxies.size) {
                val galaxy2 = galaxies[j]
                //println("galaxy2 $galaxy2")
                val minX = min(galaxy.first, galaxy2.first)
                val maxX = max(galaxy.first, galaxy2.first)
                var countX = 0L
                for(x in minX..maxX) {
                    if(colsWithoutGalaxies.contains(x)) {
                        countX += times
                    }
                }

                val minY = min(galaxy.second, galaxy2.second)
                val maxY = max(galaxy.second, galaxy2.second)
                var countY = 0L
                for (y in minY..maxY) {
                    if(rowsWithoutGalaxies.contains(y)) {
                        countY += times
                    }
                }
                // println("countX: $countX")
                // println("countY: $countY")

                val manhattan = abs(galaxy.first - galaxy2.first) + abs(galaxy.second - galaxy2.second)
                // println("manhattan $manhattan")

                sum += manhattan + countX + countY
            }
        }
        println(sum)
    }

    private fun star2(scanner: Scanner, times: Long) {
        star1(scanner, times - 1)
    }
}