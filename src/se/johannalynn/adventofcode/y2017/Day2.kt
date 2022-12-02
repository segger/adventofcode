package se.johannalynn.adventofcode.y2017

import se.johannalynn.adventofcode.y2017.Day.start
import java.util.*

/**
 *
 */
object Day2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(2, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var checksum = 0
        while(scanner.hasNextLine()) {
            val row = scanner.nextLine().split("\\s".toRegex()).map {
                it.trim().toInt()
            }
            checksum += (row.maxOrNull()!! - row.minOrNull()!!)
        }
        println(checksum)
    }

    private fun findEvenlyDivisibleValues(list: List<Int>): Int {
        for (i in list.indices) {
            val denominator = list[i]
            for (j in (i+1) until (list.size-1)) {
                val numerator = list[j]
                if (numerator % denominator == 0) return numerator / denominator
            }
        }
        return 0
    }

    private fun star2(scanner: Scanner) {
        var division = 0
        while(scanner.hasNextLine()) {
            val row = scanner.nextLine().split("\\s".toRegex()).map {
                it.trim().toInt()
            }.sorted()
            division += findEvenlyDivisibleValues(row)
        }
        println(division)
    }
}