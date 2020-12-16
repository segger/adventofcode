package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*
import kotlin.math.abs

object Day13 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(13, false)

        star1(scanner)
        // star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        while (scanner.hasNextLine()) {
            val startTime = Integer.parseInt(scanner.nextLine())
            val busses = scanner.nextLine().split(",")
            var found = false
            var c = startTime
            while (!found) {
                busses.forEach {
                    if(it != "x") {
                        val bus = Integer.parseInt(it)
                        if (c % bus == 0) {
                            found = true
                            val wait = c - startTime
                            val answer = wait * bus
                            println(answer)
                        }
                    }
                }
                c++
            }
        }

    }

    private fun star2(scanner: Scanner) {

    }
}