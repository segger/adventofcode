package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*
import kotlin.math.exp

object Day1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(1, false)

        // star1(scanner)
        star2(scanner)
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
        val input = mutableListOf<Int>()
        while (scanner.hasNextLine()) {
            val nbr = Integer.parseInt(scanner.nextLine())
            input.add(nbr)
        }
        input.sort()
        val expenses = input.toIntArray()
        //expenses.forEach { println(it) }
        var p1 = expenses.size - 1
        var p2 = expenses.size - 2
        var p3 = 0
        while(p1 > 2) {
            var sum1 = expenses[p1] + expenses[p2]
            while (sum1 >= 2020 && p2 > p3) {
                // println("p1 $p1, p2 $p2, p3 $p3")
                // println("sum1 $sum1")
                p2--
                sum1 = expenses[p1] + expenses[p2]
            }
            var sum = expenses[p1] + expenses[p2] + expenses[p3]
            while (sum <= 2020) {
                // println("p1 $p1, p2 $p2, p3 $p3")
                // println("SUM $sum")
                if (sum == 2020) {
                    println(expenses[p1] * expenses[p2] * expenses[p3])
                }
                p3++
                sum = expenses[p1] + expenses[p2] + expenses[p3]
            }
            p1--
            p2 = p1 - 1
            p3 = 0
        }
    }

}