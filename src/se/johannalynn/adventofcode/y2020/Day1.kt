package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(1, false)

        star1(scanner)
        //star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val searchFor = mutableMapOf<Int, Int>()
        while (scanner.hasNextLine()) {
            val nbr = Integer.parseInt(scanner.nextLine())
            if (searchFor.contains(nbr)) {
                println(nbr * searchFor[nbr]!!)
            } else {
                searchFor[(2020 - nbr)] = nbr
            }
        }
    }

    private fun star2(scanner: Scanner) {

    }

}