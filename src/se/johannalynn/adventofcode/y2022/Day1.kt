package se.johannalynn.adventofcode.y2022

import se.johannalynn.adventofcode.y2022.Day.start
import java.util.*

object Day1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(1, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var max = 0
        var sum = 0
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            if (next.isEmpty()) {
                if (sum > max) {
                    max = sum
                }
                sum = 0
            } else {
                val nbr = next.toInt()
                sum += nbr
            }
        }
        if (sum > max) {
            max = sum
        }
        println("max: $max")
    }

    private fun star2(scanner: Scanner) {
        val elfs = mutableListOf<Int>()
        var sum = 0
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            if (next.isEmpty()) {
                elfs.add(sum)
                sum = 0
            } else {
                val nbr = next.toInt()
                sum += nbr
            }
        }
        elfs.add(sum)
        val total = elfs.sorted().takeLast(3).sum()

        println("total: $total")
    }
}