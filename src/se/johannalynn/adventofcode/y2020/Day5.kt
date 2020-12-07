package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day5 {

    /*
    FBFBBFFRLR - 357
BFFFBBFRRR - 567
FFFBBBFRRR - 119
BBFFBBFRLL - 820
     */

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(5, false)

        star1(scanner)
        // star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var max = 0
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine()
            val row = input.substring(0, input.length - 3)
            val rowNum = calc(127, 0, 'F', 'B', row)
            val column = input.substring(input.length - 3)
            val colNum = calc(7, 0, 'L', 'R', column)
            // println("$row - $column")
            val seatID = rowNum * 8 + colNum
            // println("$rowNum $colNum = $seatID")
            if (seatID > max) {
                max = seatID
            }
        }

        // fold
        // seats.sort()
        println(max)
    }

    private fun calc(upperRange: Int, lowerRange: Int, lowerChar: Char, upperChar: Char, input: String): Int {
        var upper = upperRange
        var lower = lowerRange
        input.forEach {
            if (it == lowerChar) {
                upper = (upper - lower) / 2 + lower
            } else if (it == upperChar) {
                lower = upper - (upper - lower) / 2
            } else {
                println("Press the panic button!")
            }
        }
        return upper
    }

    private fun star2(scanner: Scanner) {

    }

}