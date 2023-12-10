package se.johannalynn.adventofcode.y2023

import se.johannalynn.adventofcode.y2023.Day.start
import java.util.*
import kotlin.math.max

object Day5 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(5, false)

        // star1(scanner)
        // star2(scanner)
        star2b(scanner)
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

    private fun star2b(scanner: Scanner) {
        // there is no overlap in resulting array!

        val seedInput = scanner.nextLine().split(":")
        val seeds = seedInput[1].trim().split(" ").map { it.toLong() }
        scanner.nextLine()

        val source = mutableListOf<LongRange>()
        for (p in seeds.indices step 2) {
            source.add(LongRange(seeds[p], seeds[p]+seeds[p+1] - 1))
        }

        val mappings = mutableListOf<LongArray>()
        val newSource = mutableListOf<LongRange>()

        var newMap = true
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine()

            // println("INPUT $input")

            if (input.isEmpty()) {
                newMap = true

                // source.sortBy { it.first }
                // mappings.sortBy { it[0] }

                var i = 0
                while (i < source.size) {
                    var seed = source[i]
                    for (mapping in mappings) {
                        val start = mapping[0]
                        val end = mapping[1]
                        val conv = mapping[2] - mapping[0]
                        // println("compare $seed with $start $end $conv")

                        if (seed.first >= start && seed.last <= end) {
                            // range inside mapping
                            source[i] = LongRange(seed.first + conv, seed.last + conv)
                            break
                        } else if (seed.first < start && seed.last >= start) {
                            // start is before, end is inside
                            if (seed.last <= end) {
                                // not fully overlap
                                source[i] = LongRange(seed.first, start - 1)
                                newSource.add(LongRange(start + conv, seed.last + conv))
                            } else {
                                // mapping inside range
                                source[i] = LongRange(seed.first, start - 1)
                                newSource.add(LongRange(start + conv, end + conv))
                                source.add(i+1, LongRange(end + 1, seed.last))
                            }
                        } else if (seed.first in start..end) {
                            // start is inside, end is after
                            newSource.add(LongRange(seed.first + conv, end + conv))
                            source[i] = LongRange(end + 1, seed.last)
                        } else {
                            // no match
                        }
                        seed = source[i]
                    }

                    i++
                }

                source.addAll(newSource)

                source.sortBy { it.first }

                newSource.clear()
                mappings.clear()
                continue
            }
            if (newMap) {
                newMap = false
            } else {
                val values = input.split(" ")
                val dest = values[0].toLong()
                val src = values[1].toLong()
                val length = values[2].toLong()

                mappings.add(longArrayOf(src, src + length - 1, dest))
            }
        }

        // source.sortBy { it.first }
        // mappings.sortBy { it[0] }

        var i = 0
        while (i < source.size) {
            var seed = source[i]
            for (mapping in mappings) {
                val start = mapping[0]
                val end = mapping[1]
                val conv = mapping[2] - mapping[0]
                // println("compare $seed with $start $end $conv")

                if (seed.first >= start && seed.last <= end) {
                    // range inside mapping
                    source[i] = LongRange(seed.first + conv, seed.last + conv)
                    break
                } else if (seed.first < start && seed.last >= start) {
                    // start is before, end is inside
                    if (seed.last <= end) {
                        // not fully overlap
                        source[i] = LongRange(seed.first, start - 1)
                        newSource.add(LongRange(start + conv, seed.last + conv))
                    } else {
                        // mapping inside range
                        source[i] = LongRange(seed.first, start - 1)
                        newSource.add(LongRange(start + conv, end + conv))
                        source.add(i+1, LongRange(end + 1, seed.last))
                    }
                } else if (seed.first in start..end) {
                    // start is inside, end is after
                    newSource.add(LongRange(seed.first + conv, end + conv))
                    source[i] = LongRange(end + 1, seed.last)
                } else {
                    // no match
                }
                seed = source[i]
            }

            i++
        }

        source.addAll(newSource)

        println(source.minOf { it.first })
    }
}
