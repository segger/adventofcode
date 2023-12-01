package se.johannalynn.adventofcode.y2023

import se.johannalynn.adventofcode.y2023.Day.start
import java.util.*

/**
 * 1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet
 */
object Day1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(1, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val calibrationValues = mutableListOf<Int>()
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            val digits = next.filter { it.isDigit() }
            val calibrationValue = "${digits[0]}${digits[digits.length-1]}"
            // println(calibrationValue)
            calibrationValues.add(calibrationValue.toInt())
        }
        println(calibrationValues.sum())
    }

    private fun star2(scanner: Scanner) {
        val calibrationValues = mutableListOf<Int>()
        while (scanner.hasNextLine()) {
            var next = scanner.nextLine()
            val numbersInInput = mapOf("1" to "1", "2" to "2", "3" to "3", "4" to "4", "5" to "5", "6" to "6", "7" to "7", "8" to "8", "9" to "9", "one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5", "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9")
                .map { mapEntry ->
                    Regex(mapEntry.key).findAll(next).map { Pair(it.range.first, mapEntry.value) }
                }.flatMap { it }.sortedBy { it.first }.map { it.second }.joinToString()

            // println(numbersInInput)
            val digits = numbersInInput.filter { it.isDigit() }
            val calibrationValue = "${digits[0]}${digits[digits.length-1]}"
            //println(calibrationValue)

            calibrationValues.add(calibrationValue.toInt())
        }
        println(calibrationValues.sum())
    }
}