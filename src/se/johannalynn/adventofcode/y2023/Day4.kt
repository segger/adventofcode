package se.johannalynn.adventofcode.y2023

import se.johannalynn.adventofcode.y2023.Day.start
import java.util.*

object Day4 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(4, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var points = 0
        while (scanner.hasNextLine()) {
            val card = scanner.nextLine().split(":")[1].split("|")
            // is unique?
            val winning = card[0].trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            val numbers = card[1].trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

            val winningNumbers = numbers.intersect(winning).size
            points += Math.pow(2.0, (winningNumbers.toDouble() - 1.0)).toInt()
        }
        println(points)
    }

    private fun star2(scanner: Scanner) {
        val cards = IntArray(219)
        var cardNbr = 0
        while (scanner.hasNextLine()) {
            val card = scanner.nextLine().split(":")[1].split("|")
            val winning = card[0].trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            val numbers = card[1].trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            val winningNumbers = numbers.intersect(winning).size
            //println("$cardNbr winning $winningNumbers")

            cards[cardNbr]++
            repeat(cards[cardNbr]) {
                for (nbr in cardNbr+1..cardNbr+winningNumbers) {
                    cards[nbr]++
                }
            }
            cardNbr++
        }
        println(cards.sum())
    }
}
