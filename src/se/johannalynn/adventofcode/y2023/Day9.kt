package se.johannalynn.adventofcode.y2023

import java.util.*

/**
 * 0 3 6 9 12 15
 * 1 3 6 10 15 21
 * 10 13 16 21 30 45
 */
object Day9 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(9, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var sum = 0L
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine().split(" ").map { it.toLong() }

            var found = false
            val next = LongArray(input.size) { input[it] }
            var size = next.size

            while(!found) {
                var i = 0
                do {
                    next[i] = next[i+1] - next[i]
                    i++
                } while(i+1 < size)

                var check = true
                for (s in 0 until size - 1) {
                    if (next[s] != 0L) {
                        check = false
                        break
                    }
                }
                if (check) {
                    found = true
                }

                size--
            }

            var history = 0L
            for(i in size until next.size) {
                history += next[i]
            }

            sum += history
        }
        println(sum)
    }

    private fun star2(scanner: Scanner) {
        var sum = 0L
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine().split(" ").map { it.toLong() }

            var found = false
            var c = 0
            val next = LongArray(input.size) { input[it] }
            val size = next.size - 1

            while(!found && c < 1000) {
                var i = size
                do {
                    next[i] = next[i] - next[i-1]
                    i--
                } while(i-1 >= c)

                // println(next.joinToString(","))

                c++

                var check = true
                for (s in c until size) {
                    if (next[s] != 0L) {
                        check = false
                        break
                    }
                }
                if (check) {
                    found = true
                }
            }

            var history = 0L
            for(i in c downTo 0) {
                history = next[i] - history
            }
            sum += history
        }
        println(sum)
    }
}