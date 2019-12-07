package se.johannalynn.adventofcode.y2019

import java.util.*
import kotlin.collections.HashSet

object Day3 {

    /*
    R8,U5,L5,D3
U7,R6,D4,L4
    R75,D30,R83,U83,L12,D49,R71,U7,L72
U62,R66,U55,R34,D71,R55,D58,R83
    R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
U98,R91,D20,R16,D67,R40,U7,R15,U6,R7
     */

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(3, false)

        star1(scanner)
    }

    fun star1(scanner: Scanner) {
        var positions = HashSet<String>()

        while(scanner.hasNextLine()) {
            val wire1 = scanner.nextLine().split(",")

            var posX = 0
            var posY = 0
            wire1.forEach {
                val direction = it.substring(0,1)
                val length = it.substring(1).toInt()

                for (i in 0..length-1) {
                    when(direction) {
                        "R" -> posX++
                        "L" -> posX--
                        "U" -> posY++
                        "D" -> posY--
                    }
                    //println("${posX},${posY}")
                    positions.add("${posX},${posY}")
                }
            }

            var minDistance = Int.MAX_VALUE
            posX = 0
            posY = 0

            val wire2 = scanner.nextLine().split(",")
            wire2.forEach {
                val direction = it.substring(0,1)
                val length = it.substring(1).toInt()

                for (i in 0..length-1) {
                    when(direction) {
                        "R" -> posX++
                        "L" -> posX--
                        "U" -> posY++
                        "D" -> posY--
                    }

                    val cross = positions.contains("${posX},${posY}")
                    if(cross) {
                        //println("CROSS AT ${posX},${posY}")
                        val distance = Math.abs(posX) + Math.abs(posY)
                        if(distance < minDistance) {
                            minDistance = distance
                        }
                    }
                }
            }
            println(minDistance)
        }
    }

}