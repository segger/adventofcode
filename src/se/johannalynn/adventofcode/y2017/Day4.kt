package se.johannalynn.adventofcode.y2017

import se.johannalynn.adventofcode.y2017.Day.start
import java.util.*

object Day4 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(4, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var sum = 0
        while(scanner.hasNextLine()) {
            val passphrase = scanner.nextLine().split(" ")
            if (passphrase.size == passphrase.toSet().size) sum++
        }
        println(sum)
    }

    private fun star2(scanner: Scanner) {
        var sum = 0
        while(scanner.hasNextLine()) {
            val passphrase = scanner.nextLine().split(" ")
            val sorted = passphrase.map { it.toSortedSet() }
            if (sorted.size == sorted.toSet().size) sum++
        }
        println(sum)
    }
}