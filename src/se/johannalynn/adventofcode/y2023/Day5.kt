package se.johannalynn.adventofcode.y2023

import se.johannalynn.adventofcode.y2023.Day.start
import java.util.*

object Day5 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(5, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val seedInput = scanner.nextLine().split(":")
        val seeds = seedInput[1].trim().split(" ").map { it.toLong() }
        scanner.nextLine()

        var first = true
        val source = mutableListOf<Long>()
        source.addAll(seeds)

        val destination = mutableListOf<Long>()
        scanner@ while (scanner.hasNextLine()) {
            val input = scanner.nextLine()

            // println("INPUT $input")

            if (input.isEmpty()) {
                first = true

                source.forEach { destination.add(it) } // aren't mapped correspond to the same destination
                source.clear()
                source.addAll(destination)
                destination.clear()

                continue@scanner
            }
            if (first) {
                /*
                println(input)
                source.forEach { print("$it ") }
                println()
                */

                first = false
            } else {
                val values = input.split(" ")
                val dest = values[0].toLong()
                val src = values[1].toLong()
                val length = values[2].toLong()

                val contains = mutableListOf<Long>()
                val sourceRange = LongRange(src, src+length)
                source.forEachIndexed { index, nbr ->
                    if (sourceRange.contains(nbr)) {
                        contains.add(nbr)
                        destination.add(dest+(nbr-src))
                    }
                }
                source.removeAll(contains)

                /*
                var c = 0
                while (c < length) {
                    if (source.remove((src+c))) {
                        destination.add((dest+c))
                    }
                    c++
                }*/
            }
        }
        source.forEach { destination.add(it) } // aren't mapped correspond to the same destination
        println(destination.minOrNull())
    }

    private fun star2(scanner: Scanner) {
        val seedInput = scanner.nextLine().split(":")
        val seeds = seedInput[1].trim().split(" ").map { it.toLong() }
        scanner.nextLine()

        var first = true
        val source = mutableListOf<LongRange>()
        for (p in seeds.indices step 2) {
            source.add(LongRange(seeds[p], seeds[p]+seeds[p+1]))
        }

        val destination = mutableListOf<LongRange>()
        scanner@ while (scanner.hasNextLine()) {
            val input = scanner.nextLine()

            // println("INPUT $input")

            if (input.isEmpty()) {
                first = true

                source.forEach { destination.add(it) } // aren't mapped correspond to the same destination
                source.clear()
                source.addAll(destination)
                destination.clear()

                // println("source size ${source.size}")

                continue@scanner
            }
            if (first) {
                /*println(input)
                source.forEach { print("$it ") }
                println() */

                first = false
            } else {
                val values = input.split(" ")
                val dest = values[0].toLong()
                val src = values[1].toLong()
                val length = values[2].toLong()

                val contains = mutableListOf<LongRange>()

                val sourceRange = LongRange(src, src+length)
                source.forEach { range ->
                    val destStart = if (sourceRange.first <= range.first) {
                        dest + range.first - sourceRange.first
                    } else {
                        dest
                    }

                    val destEnd = if (sourceRange.last >= range.last) {
                        dest + (range.last - src)
                    } else {
                        dest + (sourceRange.last - src)
                    }

                    val containStart = if (sourceRange.first <= range.first) {
                        range.first
                    } else {
                        sourceRange.first
                    }

                    val contain = sourceRange.filter { it in range }
                    if (contain.isNotEmpty()) {
                        val containAsRange = LongRange(containStart, containStart + contain.size - 1)
                        contains.add(containAsRange)
                        val destAsRange = LongRange(destStart, destEnd)
                        destination.add(destAsRange)
                    }
                }

                source.removeAll(contains)
            }
        }
        source.forEach { destination.add(it) } // aren't mapped correspond to the same destination
        println(destination.minOf { it.first })
    }
}
