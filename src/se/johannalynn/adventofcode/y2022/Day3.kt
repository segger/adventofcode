package se.johannalynn.adventofcode.y2022

import se.johannalynn.adventofcode.y2022.Day.start
import java.util.*

object Day3 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(3, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var sum = 0
        while (scanner.hasNextLine()) {
            val rucksack = scanner.nextLine()
            val first = rucksack.take(rucksack.length / 2)
            val last = rucksack.takeLast(rucksack.length / 2)
            val in_both = first.toSet().intersect(last.toSet())
            val value = in_both.map {
                if (it.isLowerCase()) {
                    it.code - 'a'.code + 1
                } else {
                    it.code - 'A'.code + 27
                }
            }
            sum += value.sum()
        }
        println(sum)
    }

    private fun star2(scanner: Scanner) {
        var sum = 0
        while (scanner.hasNextLine()) {
            val elf1 = scanner.nextLine()
            val elf2 = scanner.nextLine()
            val elf3 = scanner.nextLine()

            val unique = elf1.toSet().intersect(elf2.toSet()).intersect(elf3.toSet())
            val badge = unique.map {
                if (it.isLowerCase()) {
                    it.code - 'a'.code + 1
                } else {
                    it.code - 'A'.code + 27
                }
            }.sum()
            sum += badge
        }
        println(sum)
    }
}