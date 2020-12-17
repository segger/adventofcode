package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.math.BigInteger
import java.util.*
import kotlin.math.abs

object Day13 {

    /*
    The earliest timestamp that matches the list 17,x,13,19 is 3417.
67,7,59,61 first occurs at timestamp 754018.
67,x,7,59,61 first occurs at timestamp 779210.
67,7,x,59,61 first occurs at timestamp 1261476.
1789,37,47,1889 first occurs at timestamp 1202161486.
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(13, true)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        while (scanner.hasNextLine()) {
            val startTime = Integer.parseInt(scanner.nextLine())
            val busses = scanner.nextLine().split(",")
            var found = false
            var c = startTime
            while (!found) {
                busses.forEach {
                    if(it != "x") {
                        val bus = Integer.parseInt(it)
                        if (c % bus == 0) {
                            found = true
                            val wait = c - startTime
                            val answer = wait * bus
                            println(answer)
                        }
                    }
                }
                c++
            }
        }

    }

    private fun star2itr(scanner: Scanner) {
        while (scanner.hasNextLine()) {
            scanner.nextLine() // not used
            val busses = scanner.nextLine().split(",")

            var found = false
            var c = 1000L // something good
            // val max = busses.filter { it != "x" }.map { Integer.parseInt(it) }.max()!!.toLong()
            while (!found) {
                c++
                val matches = BooleanArray(busses.size)
                busses.forEachIndexed { idx, it ->
                    if (it != "x") {
                        val bus = it.toLong()
                        val diff = idx.toLong()
                        val match = (c + diff) % bus == 0L
                        // println("$c, $diff, $bus => $match")
                        if (match) {
                            matches[idx] = true
                        }
                    } else {
                        matches[idx] = true
                    }
                }
                if(matches.all { it }) {
                    found = true
                }
            }
            println(c)
        }
    }

    private fun star2(scanner: Scanner) {
        while (scanner.hasNextLine()) {
            scanner.nextLine() // not used
            val busses = scanner.nextLine().split(",")

            val step = busses.filter { it != "x" }.map { it.toBigInteger() }.max()!!
            // println(step)

            val stepBus = 100000000000000L.toBigInteger() / step
            // val stepBus = 1000000L / step
            // println(stepBus)

            val start = busses.mapIndexed { idx, it ->
                if (it != "x") {
                    val bus = it.toBigInteger()
                    val diff = idx.toBigInteger()
                    if (bus == step) {
                        stepBus * bus - diff
                    } else {
                        BigInteger.ZERO
                    }
                } else {
                    BigInteger.ZERO
                }
            }.filter { it != BigInteger.ZERO }.first()
            // println(start)

            val realBusses = busses.mapIndexed { idx, it ->
                if (it != "x") {
                    Pair(it, idx)
                } else {
                    null
                }
            }.filterNotNull()

            var found = false

            val mod0 = busses.first().toBigInteger()
            // var c = 100000000000000L.toBigInteger() // something good
            var c = 1000L.toBigInteger() // something good
            // val max = busses.filter { it != "x" }.map { Integer.parseInt(it) }.max()!!.toLong()
            while (!found) {
                // c += step
                c++

                var count = 0
                val itr = realBusses.iterator()
                while(itr.hasNext()) {
                    val pair = itr.next()
                    val bus = pair.first.toBigInteger()
                    val diff = pair.second.toBigInteger()
                    val match = (c + diff) % bus == BigInteger.ZERO
                    if (match) {
                        count++
                    } else {
                        break
                    }
                }
                if (count == realBusses.size) {
                    found = true
                }
            }
            println(c)
        }
    }
}