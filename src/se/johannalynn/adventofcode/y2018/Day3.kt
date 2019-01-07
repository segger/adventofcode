package se.johannalynn.adventofcode.y2018

import java.io.File
import java.util.*

object Day3 {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Day3")

        val inFileName = "2018/input/day3.txt"
        val scanner = Scanner(File(inFileName))

        star1(scanner)
    }

    private fun star1(scanner: Scanner) {
        var fabric = Array(1000) { IntArray(1000) }

        var count = 0
        while(scanner.hasNextLine()) {
            val elf = scanner.nextLine().split(" ")

            val id = elf[0].substring(1)
            val pos = elf[2].substring(0, elf[2].length - 1)
            val position = pos.split(",")
            val left = position[0].toInt()
            val top = position[1].toInt()
            val dimension = elf[3].split("x")
            val width = dimension[0].toInt()
            val height = dimension[1].toInt()

            for(i in IntProgression.fromClosedRange(left, left + width - 1, 1)) {
                for(j in IntProgression.fromClosedRange(top, top + height - 1, 1)) {
                    val square = fabric[i][j]
                    if (square == 0) {
                        fabric[i][j]++
                    } else if(square == 1) {
                        count++
                        fabric[i][j]++
                    }
                }
            }
        }

        println(count)
    }
}