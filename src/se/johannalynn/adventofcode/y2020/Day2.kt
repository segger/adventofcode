package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(2, false)

        star1(scanner)
        // star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var count = 0
        while (scanner.hasNextLine()) {
            val row = scanner.nextLine()
            val parts = row.split(":")
            val rules = parts[0].trim().split(" ")
            val nbrOf = rules[0].split("-")
            val min = Integer.parseInt(nbrOf[0])
            val max = Integer.parseInt(nbrOf[1])
            val letter = rules[1].trim()
            val regex = letter.toRegex()
            val password = parts[1].trim()

            val nbrOfMatches = regex.findAll(password).count()
            if(nbrOfMatches in min..max) {
                count++
            }
        }
        println(count)
    }

    private fun star2(scanner: Scanner) {

    }

}