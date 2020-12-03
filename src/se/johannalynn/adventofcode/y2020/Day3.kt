package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*
import kotlin.math.exp

object Day3 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(3, false)

        star1(scanner)
        // star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var count = 0
        var right = 3
        var down = 0
        while (scanner.hasNextLine()) {
            if (down == 0) scanner.nextLine()
            val row = scanner.nextLine()
            // println(row[right % row.length])
            if (row[right % row.length] == '#') {
                count++
            }
            down++
            right += 3
        }
        println(count)
    }

    private fun star2(scanner: Scanner) {

    }

}