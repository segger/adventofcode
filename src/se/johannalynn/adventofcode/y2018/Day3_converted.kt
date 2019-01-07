package se.johannalynn.adventofcode.y2018

import java.io.File
import java.io.FileNotFoundException
import java.util.Scanner

object Day3_converted {

    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        println("Day3_java")

        // #1 @ 1,3: 4x4
        val inFileName = "2018/input/day3_pre1.txt"
        val scanner = Scanner(File(inFileName))

        star1(scanner)
    }

    private fun star1(scanner: Scanner) {
        val fabric = Array(1000) { IntArray(1000) }

        var count = 0
        while (scanner.hasNextLine()) {
            val elf = scanner.nextLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val id = Integer.valueOf(elf[0].substring(1))
            val pos = elf[2].substring(0, elf[2].length - 1)
            val position = pos.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val left = Integer.valueOf(position[0])
            val top = Integer.valueOf(position[1])
            val dimension = elf[3].split("x".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val width = Integer.valueOf(dimension[0])
            val height = Integer.valueOf(dimension[1])

            for (i in left until left + width) {
                for (j in top until top + height) {
                    val square = fabric[i][j]
                    if (square == 0) {
                        fabric[i][j]++
                    } else if (square == 1) {
                        count++
                        fabric[i][j]++
                    }
                }
            }
        }

        println(count)

    }
}
