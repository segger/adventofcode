package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*
import se.johannalynn.adventofcode.y2024.Day13.Button
import kotlin.collections.LinkedHashSet
import kotlin.math.abs
import kotlin.math.pow

object Day14 {

    @JvmStatic
    fun main(args: Array<String>) {
        val pre = false
        val scanner = start(14, pre)

        // star1(scanner, pre)
        star2(scanner)
    }

    private fun star1(scanner: Scanner, pre: Boolean) {
        var maxX = 101
        var maxY = 103
        if (pre) {
            maxX = 11
            maxY = 7
        }

        val middleX = maxX / 2
        val middleY = maxY / 2

        val seconds = 100

        var quadrantOne = 0
        var quadrantTwo = 0
        var quadrantThree = 0
        var quadrantFour = 0
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split(" ")
            val pos = Regex("p=([-]*[0-9]+),([-]*[0-9]+)").find(next[0])
            val mov = Regex("v=([-]*[0-9]+),([-]*[0-9]+)").find(next[1])
            val posX = pos!!.groupValues[1].toInt()
            val posY = pos.groupValues[2].toInt()
            val movX = mov!!.groupValues[1].toInt()
            val movY = mov.groupValues[2].toInt()

            val vx = movX * seconds
            val xtmp = (vx + posX) % maxX
            val x = (maxX + xtmp) % maxX
            val vy = movY * seconds
            val ytmp = (vy + posY) % maxY
            val y = (maxY + ytmp) % maxY

            /*
            if (posX == 0 && posY == 0) {
                println("x=$x y=$y vx=$vx vy=$vy")
            } */

            if (x < middleX) {
                if (y < middleY) {
                    quadrantOne++
                } else if (y > middleY) {
                    quadrantTwo++
                }
            } else if (x > middleX) {
                if (y < middleY) {
                    quadrantThree++
                } else if (y > middleY) {
                    quadrantFour++
                }
            }
        }

        val safetyFactor = quadrantOne * quadrantTwo * quadrantThree * quadrantFour
        println("safetyFactor=$safetyFactor")

        // line, take multiplier seconds of movement, add startposition, take modulo to teleport
    }

    private fun star2(scanner: Scanner) {
        val maxX = 101
        val maxY = 103

        val robots = mutableListOf<Robot>()
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split(" ")
            val pos = Regex("p=([-]*[0-9]+),([-]*[0-9]+)").find(next[0])
            val mov = Regex("v=([-]*[0-9]+),([-]*[0-9]+)").find(next[1])
            val posX = pos!!.groupValues[1].toInt()
            val posY = pos.groupValues[2].toInt()
            val movX = mov!!.groupValues[1].toInt()
            val movY = mov.groupValues[2].toInt()

            robots.add(Robot(posX, posY, movX, movY))
        }

        val fromSeconds = 2000
        val toSeconds = 10000
        for (seconds in fromSeconds..toSeconds) {
            val positions = mutableSetOf<Pair<Int, Int>>()
            for (robot in robots) {
                val vx = robot.movX * seconds
                val xtmp = (vx + robot.x) % maxX
                val x = (maxX + xtmp) % maxX
                val vy = robot.movY * seconds
                val ytmp = (vy + robot.y) % maxY
                val y = (maxY + ytmp) % maxY

                positions.add(Pair(x, y))
            }

            if (positions.groupBy { it.first }.maxOf { it.value.size } > 30) {
                println("SECONDS $seconds")
                draw(positions, maxX, maxY)
                println()
            }
        }
        // 10299 - too high
        // 1053 - too low
    }

    data class Robot(val x: Int, val y: Int, val movX: Int, val movY: Int)

    private fun draw(robots: Set<Pair<Int, Int>>, maxX: Int, maxY: Int) {
        for (i in 0..maxX) {
            for (j in 0..maxY) {
                if (robots.contains(Pair(i, j))) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }
    }
}
