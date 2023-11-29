package se.johannalynn.adventofcode.y2018

import java.io.File
import java.io.FileNotFoundException
import java.util.*

object Day12 {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Day12")

        val inFileName = "input/2018/day12_pre.txt"
        val scanner = Scanner(File(inFileName))

        star1(scanner);
        // star2(scanner)

        scanner.close()
    }

    private fun star1(scanner: Scanner) {
        while (scanner.hasNextLine()) {
            scanner.nextLine()
        }
    }

    private fun star2(scanner: Scanner) {
    }
}
