package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*
import kotlin.collections.LinkedHashSet
import kotlin.math.abs
import kotlin.math.pow

object Day7 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(7, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {

        var calibration = 0L
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split(":")
            val value = next[0].toLong()
            val operators = next[1].split("\\s+".toRegex()).filter { it.isNotBlank() }.map { it.toLong() }
            var result = mutableListOf(operators[0])
            for (i in 1 until operators.size) {
                val tmpResult = mutableListOf<Long>()
                result.forEach {
                    tmpResult.add(it + operators[i])
                    tmpResult.add(it * operators[i])
                }
                result = tmpResult
            }

            if (result.contains(value)) {
                calibration += value
            }
        }

        println(calibration)
    }

    private fun star2(scanner: Scanner) {

        var calibration = 0L
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split(":")
            val value = next[0].toLong()
            val operators = next[1].split("\\s+".toRegex()).filter { it.isNotBlank() }.map { it.toLong() }
            var result = mutableListOf(operators[0])
            for (i in 1 until operators.size) {
                val tmpResult = mutableListOf<Long>()
                result.forEach {
                    tmpResult.add(it + operators[i])
                    tmpResult.add(it * operators[i])
                    tmpResult.add((it.toString() + operators[i].toString()).toLong())
                }
                result = tmpResult
            }

            if (result.contains(value)) {
                calibration += value
            }
        }

        println(calibration)
    }
}
