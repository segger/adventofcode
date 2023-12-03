package se.johannalynn.adventofcode.y2023

import se.johannalynn.adventofcode.y2023.Day.start
import java.util.*

object Day3 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(3, false)

        // star1(scanner)
        star2(scanner)
    }

    data class Numbers(val row: Int, val range: IntRange, val value: Int, var partNumber: Boolean)

    private fun star1(scanner: Scanner) {
        val numbers = mutableListOf<Numbers>()
        val symbols = mutableListOf<Pair<Int, Int>>()
        var row = 0
        while (scanner.hasNextLine()) {
            val rowInput = scanner.nextLine()
            Regex("[0-9]+").findAll(rowInput)
                .forEach { matchResult ->
                    numbers.add(Numbers(row, matchResult.range, matchResult.value.toInt(), false))
            }
            Regex("[^0-9.]+").findAll(rowInput).forEach {
                symbols.add(Pair(row, it.range.first))
            }
            row++
        }
        symbols.forEach {symbol ->
            // println("symbol ${symbol.first} - ${symbol.second}")
            numbers.forEach {
                if ((it.row-1..it.row+1).contains(symbol.first)) {
                    if ((it.range.first-1..it.range.last+1).contains(symbol.second)) {
                        it.partNumber = true
                    }
                }
            }
        }
        val sumPartNumbers = numbers.filter { it.partNumber }.sumOf { it.value }
        println(sumPartNumbers)
    }

    data class PartNumber(val row: Int, val range: IntRange, val value: Int)
    data class Gear(val row: Int, val col: Int, val partNumbers: MutableList<Int> = mutableListOf())

    private fun star2(scanner: Scanner) {
        val partNumbers = mutableListOf<PartNumber>()
        val symbols = mutableListOf<Gear>()
        var row = 0
        while (scanner.hasNextLine()) {
            val rowInput = scanner.nextLine()
            Regex("[0-9]+").findAll(rowInput)
                .forEach { matchResult ->
                    partNumbers.add(PartNumber(row, matchResult.range, matchResult.value.toInt()))
                }
            Regex("[^0-9.]+").findAll(rowInput).forEach {
                symbols.add(Gear(row, it.range.first))
            }
            row++
        }
        symbols.forEach {symbol ->
            // println("symbol ${symbol.first} - ${symbol.second}")
            partNumbers.forEach {
                if ((it.row-1..it.row+1).contains(symbol.row)) {
                    if ((it.range.first-1..it.range.last+1).contains(symbol.col)) {
                        symbol.partNumbers.add(it.value)
                    }
                }
            }
        }
        val gearRatios =
            symbols.filter { it.partNumbers.size == 2 }.sumOf { it.partNumbers.reduce { acc, nbr -> acc * nbr } }
        println(gearRatios)
    }
}