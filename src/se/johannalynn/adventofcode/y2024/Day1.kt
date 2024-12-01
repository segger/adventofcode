package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*

object Day1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(1, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            val digits = next.split("\\s+".toRegex())
            list1.add(digits[0].toInt())
            list2.add(digits[1].toInt())
        }
        list1.sort()
        list2.sort()

        var sum = 0
        list1.forEachIndexed { index, i ->
            sum += Math.abs(i - list2[index])
            // println("i $i - ${list2[index]} = ${Math.abs(i - list2[index])}")
        }
        println(sum)
    }

    private fun star2(scanner: Scanner) {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            val digits = next.split("\\s+".toRegex())
            list1.add(digits[0].toInt())
            list2.add(digits[1].toInt())
        }

        var sum = 0
        list1.forEach { left ->
            var count = 0
            list2.forEach { right ->
                if (left == right) {
                    count++
                }
            }
            sum += (left * count)
        }
        println(sum)
    }
}
