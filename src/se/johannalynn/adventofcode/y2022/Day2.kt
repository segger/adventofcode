package se.johannalynn.adventofcode.y2022

import se.johannalynn.adventofcode.y2022.Day.start
import java.util.*

object Day2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(2, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var sum = 0
        while (scanner.hasNextLine()) {
            val round = scanner.nextLine().split(" ")
            val point = when(round[1]) {
                "X" -> 1 + if ("C" == round[0]) 6 else if ("A" == round[0]) 3 else 0
                "Y" -> 2 + if ("A" == round[0]) 6 else if ("B" == round[0]) 3 else 0
                "Z" -> 3 + if ("B" == round[0]) 6 else if ("C" == round[0]) 3 else 0
                else -> 0
            }
            // println("point $point")
            sum += point
        }
        println("sum $sum")
    }

    private fun star2(scanner: Scanner) {
        var sum = 0
        while (scanner.hasNextLine()) {
            val round = scanner.nextLine().split(" ")
            val point = when(round[1]) {
                "X" -> if ("C" == round[0]) 2 else if ("A" == round[0]) 3 else 1 // lose
                "Y" -> if ("A" == round[0]) 4 else if ("B" == round[0]) 5 else 6 // draw
                "Z" -> if ("B" == round[0]) 9 else if ("C" == round[0]) 7 else 8 // win
                else -> 0
            }
            // println("point $point")
            sum += point
        }
        println("sum $sum")
    }
}