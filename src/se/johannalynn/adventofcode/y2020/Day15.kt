package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day15 {

    /*
    0,3,6 436
    1,3,2, the 2020th number spoken is 1.
2,1,3, the 2020th number spoken is 10.
1,2,3, the 2020th number spoken is 27.
2,3,1, the 2020th number spoken is 78.
3,2,1, the 2020th number spoken is 438.
3,1,2, the 2020th number spoken is 1836.

Given 0,3,6, the 30000000th number spoken is 175594.
Given 1,3,2, the 30000000th number spoken is 2578.
Given 2,1,3, the 30000000th number spoken is 3544142.
Given 1,2,3, the 30000000th number spoken is 261214.
Given 2,3,1, the 30000000th number spoken is 6895259.
Given 3,2,1, the 30000000th number spoken is 18.
Given 3,1,2, the 30000000th number spoken is 362.
     */

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(15, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val result = IntArray(2021)
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine().split(",")
            input.forEachIndexed { idx, input ->
                result[idx] = Integer.parseInt(input)
            }
            var idx = input.size - 1
            while (idx < result.size - 1) {
                val number = result[idx]

                var newNumber = 0
                var idx2 = idx - 1
                while(idx2 >= 0) {
                    val comp = result[idx2]
                    // println("  $idx2 : $comp")
                    if(comp == number) {
                        // calc
                        val diff = idx - idx2
                        newNumber = diff
                        // println("    $diff - $newNumber")
                        break
                    }
                    idx2--
                }
                result[++idx] = newNumber
            }
        }

        result.forEach {
            print("$it, ")
        }
        println()

        println(result[2019])
    }

    private fun star2(scanner: Scanner) {
        val result = mutableMapOf<Int, MutableList<Int>>()
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine().split(",")
            input.forEachIndexed { idx, input ->
                val number = Integer.parseInt(input)
                var idxes = result[number]
                if (idxes == null) {
                    idxes = mutableListOf()
                }
                idxes.add(idx + 1)
                result[number] = idxes
            }
            var idx = input.size
            var lastnumber = Integer.parseInt(input[idx - 1])
            // println("$lastnumber - $idx")
            while (idx < 30000000) {
                idx++
                // println("idx: $idx, lastnumber: $lastnumber")
                var newNumber: Int
                val idxes = result[lastnumber]
                if (idxes == null || idxes.size <= 1) {
                    newNumber = 0
                } else {
                    val last = idxes[idxes.size - 1]
                    val next = idxes[idxes.size - 2]
                    val diff = last - next
                    // println("$last - $next = $diff")
                    newNumber = diff
                }
                // print("$newNumber, ")

                var newIdxes = result[newNumber]
                if (newIdxes == null) {
                    newIdxes = mutableListOf()
                }
                newIdxes.add(idx)
                result[newNumber] = newIdxes

                lastnumber = newNumber
            }
            // println()
            println(lastnumber)
        }
    }
}