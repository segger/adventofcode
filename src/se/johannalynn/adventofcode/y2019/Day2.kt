package se.johannalynn.adventofcode.y2019

import java.util.*

object Day2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(2, false)

        star1(scanner)
    }

    fun star1(scanner: Scanner) {
        while(scanner.hasNextLine()) {
            val next = scanner.nextLine().split(",").map { it.toInt() }.toMutableList()
            var idx = 0
            var start = next[idx]
            while(start != 99) {
                if(start == 1) {
                    next[next[idx + 3]] = next[next[idx + 1]] + next[next[idx + 2]]
                } else if(start == 2) {
                    next[next[idx + 3]] = next[next[idx + 1]] * next[next[idx + 2]]
                } else {
                    print("Error")
                    break
                }
                idx += 4
                start = next[idx]
            }
            print(next[0])
        }
    }
}