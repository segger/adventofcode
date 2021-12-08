package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.Integer.*
import java.lang.Math.abs
import java.util.*

object Day7 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(7, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val crabs = scanner.nextLine().split(",").map { it.toInt() }.sorted()
        var min = MAX_VALUE
        for (pos in crabs.first()..crabs.last()) {
            val fuel = crabs.map { abs(it-pos) }.sum()
            if (fuel < min) {
                min = fuel
            }
        }
        println("min_fuel $min")
    }

    private fun star2(scanner: Scanner) {
        val crabs = scanner.nextLine().split(",").map { it.toInt() }.sorted()
        var min = MAX_VALUE
        for (pos in crabs.first()..crabs.last()) {
            val fuel = crabs.map {
                val diff = abs(it-pos)
                val triangular = diff * (diff+1)/2
                triangular
            }.sum()

            if (fuel < min) {
                min = fuel
            }
        }
        println("min_fuel $min")
    }
}