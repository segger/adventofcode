package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.util.*

object Day2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(2, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var horizontal = 0
        var vertical = 0
        while (scanner.hasNextLine()) {
            val course = scanner.nextLine().split(" ")
            val direction = course[0]
            val position = course[1].toInt()
            when (direction) {
                "forward" -> horizontal += position
                "up" -> vertical -= position
                "down" -> vertical += position
            }
        }
        val answer = horizontal * vertical
        println("answer $answer")
    }

    private fun star2(scanner: Scanner) {
        var horizontal = 0
        var vertical = 0
        var aim = 0
        while (scanner.hasNextLine()) {
            val course = scanner.nextLine().split(" ")
            val direction = course[0]
            val position = course[1].toInt()
            when (direction) {
                "forward" -> {
                    horizontal += position
                    vertical += (aim*position)
                }
                "up" -> aim -= position
                "down" -> aim += position
            }
        }
        val answer = horizontal * vertical
        println("answer $answer")
    }
}