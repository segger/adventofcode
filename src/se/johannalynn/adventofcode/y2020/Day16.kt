package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day16 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(16, false)

        star1(scanner)
        // star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val tickets = mutableListOf<IntArray>()
        val rules = mutableListOf<IntRange>()
        scanner.useDelimiter("\\n\\n")
        while(scanner.hasNext()) {
            val input = scanner.next()
            val row = input.split("\n")
            if (row[0].startsWith("your ticket")) {
                val yourticket = row[1]
                // println(yourticket)
            } else if (row[0].startsWith("nearby tickets")){
                row.forEachIndexed { idx, ticket ->
                    if (idx != 0) {
                        val ticket = ticket.split(",")
                                .map {
                                    Integer.parseInt(it)
                                }.toIntArray()
                        tickets.add(ticket)
                    }
                }
                // println("${tickets.size}")
            } else {
                row.forEach {
                    val rule = it.split(":")
                    val constraints = rule[1].trim().split(" or ")
                    constraints.forEach {
                        // val constraint = it.trim().replace("-",",")
                        val numbers = it.trim().split("-")
                                .map {
                                    Integer.parseInt(it)
                                }
                        val constraint = IntRange(numbers[0], numbers[1])
                        rules.add(constraint)
                    }
                }
                // println("${rules.size}")
            }
        }
        var count = 0
        tickets.forEach {ticket ->
            ticket.forEach {nbr ->
                var invalidNbr = true
                rules.forEach {
                    if(it.contains(nbr)) {
                        invalidNbr = false
                    }
                }
                if (invalidNbr) {
                    count += nbr
                }
            }
        }
        println(count)
    }

    private fun star2(scanner: Scanner) {

    }
}