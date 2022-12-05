package se.johannalynn.adventofcode.y2022

import se.johannalynn.adventofcode.y2022.Day.start
import java.util.*

object Day4 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(4, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var sum = 0
        while(scanner.hasNextLine()) {
            val row = scanner.nextLine().split(",")
            val assignment1 = row[0].split("-")
            val assignment2 = row[1].split("-")
            sum += check(assignment1[0].toInt(), assignment2[0].toInt(), assignment1[1].toInt(), assignment2[1].toInt())
        }
        println("sum $sum")
    }

    private fun check(a: Int, b: Int, c: Int, d: Int): Int {
        if (a == b && c == d) return 1
        if (a <= b && c >= d) return 1
        if (b <= a && d >= c) return 1
        return 0
    }

    private fun star2(scanner: Scanner) {
        var sum = 0
        while(scanner.hasNextLine()) {
            val row = scanner.nextLine().split(",")
            val assignment1 = row[0].split("-")
            val assignment2 = row[1].split("-")
            sum += check2(assignment1[0].toInt(), assignment2[0].toInt(), assignment1[1].toInt(), assignment2[1].toInt())
        }
        println("sum $sum")
    }

    private fun check2(a: Int, b: Int, c: Int, d: Int): Int {
        if (b in a..c) return 1
        if (a in b .. d) return 1
        return 0
    }
}