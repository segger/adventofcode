package se.johannalynn.adventofcode.y2017

import se.johannalynn.adventofcode.y2017.Day.start
import java.util.*

/**
 * 1029
 */
object Day1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(1, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        scanner.useDelimiter("")
        var sum = 0
        var current = scanner.nextInt()
        val first = current
        while(scanner.hasNextInt()) {
            val input = scanner.nextInt()
            if (input == current) sum += current
            current = input
        }
        if (first == current) sum += current
        println(sum)
    }

    private fun star2(scanner: Scanner) {
        scanner.useDelimiter("")
        val nbrs = mutableListOf<Int>()
        while(scanner.hasNextInt()) {
            nbrs.add(scanner.nextInt())
        }
        var sum = 0
        nbrs.forEachIndexed { index, i ->
            val checkIdx = (index + (nbrs.size/2)) % nbrs.size
            if (i == nbrs[checkIdx]) sum += i
        }
        println("sum $sum")
    }
}