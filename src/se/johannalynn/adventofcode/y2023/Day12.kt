package se.johannalynn.adventofcode.y2023

import java.util.*

object Day12 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(12, true)

        star1(scanner)
        // star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine().split(" ")
            val row = input[0]
            val groups = input[1].split(",")

            println("row $row")
            println("groups $groups")

            for (group in groups) {
                println("group $group")
                "[#?]{${group}}"
            }

            Regex("[#?]{${groups[0]}}[.?]{1}[#?]{${groups[1]}}[#?]{${groups[2]}}").find(row)?.let {
                println(it.value)
                println(it.range)
            }

            /*
            var idx = 0
            for (group in groups) {
                println("idx $idx")
                Regex("[#\\?]{$group}[\\.\\?$]{1}").find(row, idx)?.let {
                    println(it.value)
                    println(it.range)
                    idx = it.range.last
                    println(idx)
                }
                println()
            }*/
        }
    }

    private fun star2(scanner: Scanner) {

    }
}