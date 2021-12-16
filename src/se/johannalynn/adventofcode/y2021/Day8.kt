package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.Integer.*
import java.lang.Math.abs
import java.util.*

object Day8 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(8, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var count = 0
        while(scanner.hasNextLine()) {
            val row = scanner.nextLine().split("|")
            val outputs = row[1].trim().split(" ")
            outputs.forEach {
                when (it.length) {
                    2, 3, 4, 7 -> count++
                }
            }
        }
        println("1, 4, 7, 8 = $count")
    }

    private fun compare(unknown: Set<Char>, four: Set<Char>): Int {
        var matches = 0
        four.forEach {
            if (unknown.contains(it)) {
                matches++
            }
        }
        if (matches == 3) {
            return 5
        } else {
            return 2
        }
    }

    private fun star2(scanner: Scanner) {
        var sum = 0
        while (scanner.hasNextLine()) {
            val row = scanner.nextLine().split("|")
            val transformed = mutableMapOf<String, String>()
            val check = mutableMapOf<Int, SortedSet<Char>>()

            val input = row[0].trim().split(" ")
            input.sortedBy { it.length }.forEach {
                val order = it.toSortedSet()
                val ordered = order.toString()
                when (it.length) {
                    2 -> {
                        check[1] = order
                        transformed[ordered] = "1"
                    }
                    3 -> {
                        check[7] = order
                        transformed[ordered] = "7"
                    }
                    4 -> {
                        check[4] = order
                        transformed[ordered] = "4"
                    }
                    5 -> {
                        if (order.containsAll(check[1]!!)) {
                            check[3] = order
                            transformed[ordered] = "3"
                        } else if (compare(order, check[4]!!) == 2) {
                            check[2] = order
                            transformed[ordered] = "2"
                        } else if (compare(order, check[4]!!) == 5) {
                            check[5] = order
                            transformed[ordered] = "5"
                        }
                    }
                    6 -> {
                        if (!order.containsAll(check[1]!!)) {
                            check[6] = order
                            transformed[ordered] = "6"
                        } else if (order.containsAll(check[4]!!)) {
                            check[9] = order
                            transformed[ordered] = "9"
                        } else {
                            check[0] = order
                            transformed[ordered] = "0"
                        }
                    }
                    7 -> transformed[ordered] = "8"
                }
            }

            /*
            7 -> 8
            6 -> 0 (middle), 6, (topright) 9 (bottomleft)
            5 -> 2, (topleft, bottomright) 3, (left) 5 (topright, bottomleft)
            4 -> 4 (1 + middle, topleft)
            3 -> 7 (1+top)
            2 -> 1
             */


            /*
            transformed.forEach {
                println(it.key + " " + it.value)
            } */

            val outputs = row[1].trim().split(" ")
            val outputNbr = StringBuffer()
            outputs.forEach {
                val ordered = it.toSortedSet().toString()
                outputNbr.append(transformed[ordered])
            }
            sum += outputNbr.toString().toInt()
        }
        println("sum: $sum")
    }
}