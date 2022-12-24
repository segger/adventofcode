package se.johannalynn.adventofcode.y2022

import se.johannalynn.adventofcode.y2022.Day.start
import java.util.*
import kotlin.math.abs

/**
 *
 * R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2


R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20
 */

object Day9 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(9, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val visitedPlaces = mutableSetOf<Pair<Int, Int>>()
        var currHeadX = 0
        var currHeadY = 0
        var currTailX = 0
        var currTailY = 0
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine().split(" ")
            val steps = line[1].toInt()
            for (i in 0 until steps) {
                val currX = currHeadX
                val currY = currHeadY

                when(line[0]) {
                    "R" -> currHeadX++
                    "L" -> currHeadX--
                    "U" -> currHeadY++
                    "D" -> currHeadY--
                }

                if (abs(currHeadX-currTailX) > 1 || abs(currHeadY-currTailY) > 1) {
                    currTailX = currX
                    currTailY = currY
                }

                visitedPlaces.add(Pair(currTailX, currTailY))
            }
        }

        println("# ${visitedPlaces.size}")
    }

    private fun star2(scanner: Scanner) {
        val visitedPlaces = mutableSetOf<Pair<Int, Int>>()

        val currentX = IntArray(10) { 0 }
        val currentY = IntArray(10) { 0 }
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine().split(" ")
            val steps = line[1].toInt()
            // println("line $line")
            for (i in 0 until steps) {

                var formerX = currentX.copyOf()
                var formerY = currentY.copyOf()

                when(line[0]) {
                    "R" -> currentX[0]++
                    "L" -> currentX[0]--
                    "U" -> currentY[0]++
                    "D" -> currentY[0]--
                }

                for(i in 1..9) {
                    if (abs(currentX[i-1]-currentX[i]) > 1 || abs(currentY[i-1]-currentY[i]) > 1) {

                        if (currentX[i-1] > currentX[i]) {
                            currentX[i]++
                        } else if (currentX[i-1] < currentX[i]) {
                            currentX[i]--
                        }

                        if (currentY[i-1] > currentY[i]) {
                            currentY[i]++
                        } else if (currentY[i-1] < currentY[i]) {
                            currentY[i]--
                        }

                    }
                }

                /*
                for (idx in 0 .. 9) {
                    print("$idx: ${currentX[idx]}, ${currentY[idx]} # ")
                }
                println() */

                visitedPlaces.add(Pair(currentX[9], currentY[9]))
            }

            /*
            for (idx in 0 .. 9) {
                println("$idx: ${currentX[idx]}, ${currentY[idx]}")
            } */

            /*
            visitedPlaces.forEach {
                println("tail at ${it.first}, ${it.second}")
            }
            println()
            */

        }

        println("# ${visitedPlaces.size}")
    }
}