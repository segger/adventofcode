package se.johannalynn.adventofcode.y2018

import java.io.File
import java.io.FileNotFoundException
import java.util.*

object Day1 {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Day1")

        val inFileName = "input/2018/day1.txt"
        val scanner = Scanner(File(inFileName))

        //star1(scanner);
        star2(scanner)

        scanner.close()
    }

    private fun star1(scanner: Scanner) {
        var start = 0
        while (scanner.hasNextLine()) {
            val frequency = Integer.parseInt(scanner.nextLine())
            start += frequency
        }
        println(start)
    }

    private fun star2(scanner: Scanner) {
        val shifts = ArrayList<Int>()
        while (scanner.hasNextLine()) {
            val frequency = Integer.parseInt(scanner.nextLine())
            shifts.add(frequency)
        }

        var current = 0
        val frequencies = HashSet<Int>()
        frequencies.add(current)
        var size = frequencies.size

        var found = false
        var i = 0
        while (!found) {
            val frequency = shifts[i]
            current += frequency
            frequencies.add(current)

            val newSize = frequencies.size
            if (size == newSize) {
                println(current)
                found = true
            }
            size = newSize
            i++
            if (i >= shifts.size) {
                i = 0
            }
        }
    }
}
