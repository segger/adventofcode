package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.util.*
import kotlin.math.pow

object Day3 {

    @JvmStatic
    fun main(args: Array<String>) {
        val usePre = false
        val scanner = start(3, usePre)

        // star1(scanner, if (usePre) 5 else 12)
        star2(scanner)
    }

    private fun star1(scanner: Scanner, nbr: Int) {
        val ones = IntArray(nbr)
        var rows = 0
        while (scanner.hasNextLine()) {
            scanner.nextLine().forEachIndexed { index, c ->
                if (c == '1') {
                    ones[index]++
                }
            }
            rows++
        }
        var gammaValue = 0.0
        var epsilonValue = 0.0
        for ((count, i) in (ones.size - 1 downTo 0).withIndex()) {
            if (ones[i] > (rows/2)) {
                gammaValue += 2.0.pow(count)
            } else {
                epsilonValue += 2.0.pow(count)
            }
        }
        val result = gammaValue * epsilonValue
        println("result ${result.toInt()}")
    }

    private fun star2(scanner: Scanner) {
        val ogr = mutableListOf<IntArray>()
        val co2 = mutableListOf<IntArray>()
        var size = 0
        while(scanner.hasNextLine()) {
            val diagnostic = scanner.nextLine().map {
                if (it == '1') {
                    1
                } else {
                    0
                }
            }.toIntArray()

            size = diagnostic.size
            ogr.add(diagnostic)
            co2.add(diagnostic)
        }

        val ogrValue = checkDiagnostics(ogr, size) { ones, zeros -> ones >= zeros }
        val co2Value = checkDiagnostics(co2, size) { ones, zeros -> ones < zeros }

        val result = ogrValue * co2Value
        println("result ${result.toInt()}")
    }

    private fun checkDiagnostics(diagnostics: List<IntArray>, size: Int, keepOnes: (ones: Int, zeros: Int) -> Boolean): Double {
        var input = diagnostics
        var position = 0
        while(input.size > 1 && position < size) {
            var ones = 0
            var zeros = 0
            input.forEach {
                if (it[position] == 1) {
                    ones++
                } else {
                    zeros++
                }
            }
            val filterDiagnostics = input.filter {
                if (keepOnes(ones, zeros)) {
                    it[position] == 1
                } else {
                    it[position] == 0
                }
            }.toMutableList()

            if (filterDiagnostics.size >= 1) {
                input = filterDiagnostics
            }
            position++
        }


        var decimalValue = 0.0
        for ((count, idx) in (input[0].size - 1 downTo 0).withIndex()) {
            val value = input[0][idx]
            if (value == 1) {
                decimalValue += 2.0.pow(count)
            }
        }
        return decimalValue
    }
}