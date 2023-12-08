package se.johannalynn.adventofcode.y2023

import se.johannalynn.adventofcode.y2023.Day.start
import java.util.*

object Day7 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(7, false)

        // star1(scanner)
        star2(scanner)
    }

    data class Cards(val cards: String, val bid: Int) : Comparable<Cards> {
        val type: Int
        val value: IntArray
        init {
            val groupBy = cards.toCharArray().groupBy { it }

            type = if (groupBy.count() == 1) {
                7
            } else if (groupBy.any { it.value.size == 4 }) {
                6
            } else if (groupBy.any { it.value.size == 3 } && groupBy.count() == 2) {
                5
            } else if (groupBy.any { it.value.size == 3 }) {
                4
            } else if (groupBy.count() == 3) {
                3
            } else if (groupBy.count() == 5) {
                1
            } else {
                2
            }

            value = cards.map { getCardValue(it) }.toIntArray()
            // println(value)
        }

        private fun getCardValue(char: Char): Int {
            if (char == 'A') return 14
            if (char == 'K') return 13
            if (char == 'Q') return 12
            if (char == 'J') return 11
            if (char == 'T') return 10
            return char.digitToInt()
        }

        override fun compareTo(other: Cards): Int {
            return if (type > other.type) {
                1
            } else if (other.type > type) {
                -1
            } else {
                var compare = 0
                var idx = 0
                while (compare == 0 && idx < value.size) {
                    compare = value[idx] - other.value[idx]
                    idx++
                }
                return compare
            }
        }
    }

    private fun star1(scanner: Scanner) {
        val first = scanner.nextLine().split(" ")
        val rankedList = mutableListOf<Cards>()
        rankedList.add(Cards(first[0], first[1].toInt()))

        var c = 0
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split(" ")
            val nextCards = Cards(next[0], next[1].toInt())

            c++

            var pos = 0
            while (pos < rankedList.size && rankedList[pos] > nextCards) {
                pos++
            }
            if (pos < rankedList.size) {
                rankedList.add(pos, nextCards)
            } else {
                rankedList.add(nextCards)
            }
        }

        /*
        rankedList.forEachIndexed { index, cards ->
            val cardsValue = cards.value.map { it }.joinToString(",")
            println("$index: ${cards.type} - $cardsValue - $cards")
        } */

        val winnings = rankedList.reversed().mapIndexed { index, cards ->
            val winning = cards.bid * (index + 1)
            // println("winning - ${cards.bid} * ${index + 1} = $winning")
            winning
        }.sum()
        println(winnings)
    }

    data class CardsWithJoker(val cards: String, val bid: Int) : Comparable<CardsWithJoker> {
        val type: Int
        val value: IntArray

        init {
            val theCards = if (cards.contains('J')) {
                // fyll alltid på den största gruppen
                val countOtherCards = cards.groupingBy { it }.eachCount().filter { it.key != 'J' }
                if (countOtherCards.isNotEmpty()) {
                    cards.replace('J', countOtherCards.toList().sortedByDescending { it.second }.first().first)
                } else {
                    cards
                }
            } else cards

            val groupBy = theCards.toCharArray().groupBy { it }

            type = if (groupBy.count() == 1) {
                7
            } else if (groupBy.any { it.value.size == 4 }) {
                6
            } else if (groupBy.any { it.value.size == 3 } && groupBy.count() == 2) {
                5
            } else if (groupBy.any { it.value.size == 3 }) {
                4
            } else if (groupBy.count() == 3) {
                3
            } else if (groupBy.count() == 5) {
                1
            } else {
                2
            }

            value = cards.map { getCardValue(it) }.toIntArray()
            // println(value)
        }

        private fun getCardValue(char: Char): Int {
            if (char == 'A') return 14
            if (char == 'K') return 13
            if (char == 'Q') return 12
            if (char == 'J') return 1
            if (char == 'T') return 10
            return char.digitToInt()
        }

        override fun compareTo(other: CardsWithJoker): Int {
            return if (type > other.type) {
                1
            } else if (other.type > type) {
                -1
            } else {
                var compare = 0
                var idx = 0
                while (compare == 0 && idx < value.size) {
                    compare = value[idx] - other.value[idx]
                    idx++
                }
                return compare
            }
        }
    }

    private fun star2(scanner: Scanner) {
        val first = scanner.nextLine().split(" ")
        val rankedList = mutableListOf<CardsWithJoker>()
        rankedList.add(CardsWithJoker(first[0], first[1].toInt()))

        var c = 0
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split(" ")
            val nextCards = CardsWithJoker(next[0], next[1].toInt())

            c++

            var pos = 0
            while (pos < rankedList.size && rankedList[pos] > nextCards) {
                pos++
            }
            if (pos < rankedList.size) {
                rankedList.add(pos, nextCards)
            } else {
                rankedList.add(nextCards)
            }
        }

        /*
        rankedList.forEachIndexed { index, cards ->
            val cardsValue = cards.value.map { it }.joinToString(",")
            println("$index: ${cards.type} - $cardsValue - $cards")
        } */

        val winnings = rankedList.reversed().mapIndexed { index, cards ->
            val winning = cards.bid * (index + 1)
            // println("winning - ${cards.bid} * ${index + 1} = $winning")
            winning
        }.sum()
        println(winnings)
    }
}
