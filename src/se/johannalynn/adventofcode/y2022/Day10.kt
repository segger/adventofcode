package se.johannalynn.adventofcode.y2022

import se.johannalynn.adventofcode.y2022.Day.start
import java.util.*

/**
 * noop
addx 3
addx -5
 */

object Day10 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(10, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var x = 1
        var cycle = 1
        var signalStrength = 0
        while(scanner.hasNextLine()) {
            val read = scanner.nextLine()
            // println("read $read at $cycle")
            val input = read.split(" ")

            var add = false
            val step = when(input[0]) {
                "noop" -> 1
                "addx" -> {
                    add = true
                    2
                }
                else -> throw RuntimeException()
            }

            for (i in 1..step) {
                if (cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220) {
                    // println("cycle $cycle, x $x")
                    signalStrength += (cycle * x)
                    // println("signal strength ${signalStrength}")
                }
                cycle++
            }

            if (add) {
                x += input[1].toInt()
                // println("x $x")
            }
        }
        println("singal strength $signalStrength")
    }

    private fun star2(scanner: Scanner) {
        var sprite = 1
        var cycle = 0
        var crt = 0
        while(scanner.hasNextLine()) {
            val read = scanner.nextLine()
            val input = read.split(" ")

            var add = false
            val step = when(input[0]) {
                "noop" -> 1
                "addx" -> {
                    add = true
                    2
                }
                else -> throw RuntimeException()
            }

            for (i in 1..step) {
                if (cycle == 40 || cycle == 80 || cycle == 120 || cycle == 160 || cycle == 200 || cycle == 240) {
                    println()
                    crt = 0
                }
                if (crt in sprite-1..sprite+1) {
                    print("#")
                } else {
                    print(".")
                }
                cycle++
                crt++
            }

            if (add) {
                sprite += input[1].toInt()
            }
        }
    }
}