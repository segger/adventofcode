package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.util.*

object Day1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(1, false)

        //star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var count = -1
        var before = 0
        while (scanner.hasNextLine()) {
            val nbr = scanner.nextLine().toInt()
            if (nbr > before) {
                count++
            }
            before = nbr
        }
        println("increase $count")
    }

    private fun star2(scanner: Scanner) {
        val window = mutableListOf<Int>()
        var measurements = 0
        var count = 0
        while (scanner.hasNextLine()) {
            val nbr = scanner.nextLine().toInt()
            window.add(nbr)
            measurements++
            if (measurements > 3) {
                val sum1 = window[measurements-1] + window[measurements-2] + window[measurements-3]
                val sum2 = window[measurements-2] + window[measurements-3] + window[measurements-4]
                if (sum1 > sum2) {
                    count++
                }
            }
        }
        println("increase $count")
    }
}