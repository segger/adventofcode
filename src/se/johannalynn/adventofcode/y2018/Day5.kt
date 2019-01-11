package se.johannalynn.adventofcode.y2018

import java.util.*

object Day5 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(5, false)

        star1(scanner)
    }

    private fun star1(scanner: Scanner) {
        while(scanner.hasNextLine()) {
            val next = scanner.nextLine()

            var before = next[0]
            var polymer = "" + before

            for((i, c) in next.withIndex()) {
                if(i == 0)
                    continue
                var match = false
                if(c.isLowerCase())
                    match = before == c.toUpperCase()
                if(c.isUpperCase())
                    match = before == c.toLowerCase()

                if(!match) {
                    polymer += c
                    before = c
                } else {
                    if(polymer.length - 2 <= 0) {
                        before = polymer[0]
                    } else {
                        before = polymer[polymer.length - 2]
                    }
                    polymer = polymer.substring(0, polymer.length - 1)
                }
            }
            println(polymer.length)
        }
    }
}