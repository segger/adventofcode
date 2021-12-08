package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.Integer.max
import java.lang.Integer.min
import java.util.*

object Day6 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(6, true)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val total = scanner.nextLine().split(",").map {
            // println("=== ${it.toInt()} ===")
            val spawns = lanternfish(it.toInt(), 80)
            // println("spawns $spawns")
            spawns
        }.sum()

        println("total $total")

        // println(lanternfish2(3, 18))
    }

    private fun lanternfish(state: Int, days: Int): Int {
        if (state >= days) {
            // println("state $state, days $days")
            return 1
        } else {
            val daysLeft = days - (state+1)
            val weeks = daysLeft / 7
            // println("state $state, days: $days, daysLeft: $daysLeft, weeks $weeks")
            var spawns = 1
            for (s in 1..weeks) {
                spawns += lanternfish(8, daysLeft - (s*7))
            }
            return spawns + lanternfish(8, daysLeft)
        }
    }

    private fun star2(scanner: Scanner) {
        println(lanternfish(3, 256))
    }
}