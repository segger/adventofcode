package se.johannalynn.adventofcode.y2023

import java.lang.System.exit
import java.util.*

/**
 * RL
 *
 * AAA = (BBB, CCC)
 * BBB = (DDD, EEE)
 * CCC = (ZZZ, GGG)
 * DDD = (DDD, DDD)
 * EEE = (EEE, EEE)
 * GGG = (GGG, GGG)
 * ZZZ = (ZZZ, ZZZ)
 *
 * LLR
 *
 * AAA = (BBB, BBB)
 * BBB = (AAA, ZZZ)
 * ZZZ = (ZZZ, ZZZ)
 */
object Day8 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(8, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val adjacencyMap = mutableMapOf<String, Pair<String, String>>()
        val instruction = scanner.nextLine().split("").filter { it.isNotBlank() }
        scanner.nextLine()
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine().split("=")
            val from = input[0].trim()
            val to = input[1].trim().split(",")
            val left = to[0].trim().replace("(","")
            val right = to[1].trim().replace(")","")
            // println("$from $left $right")

            adjacencyMap[from] = Pair(left, right)
        }
        var steps = 0
        var idx = 0
        var current = "AAA"
        while (current != "ZZZ") {
            when(instruction[idx % instruction.size]) {
                "L" -> {
                    current = adjacencyMap[current]!!.first
                }
                "R" -> {
                    current = adjacencyMap[current]!!.second
                }
            }
            idx++
            steps++
        }
        println(steps)
    }

    private fun star2(scanner: Scanner) {
        val adjacencyMap = mutableMapOf<String, Pair<String, String>>()
        val instruction = scanner.nextLine().split("").filter { it.isNotBlank() }
        scanner.nextLine()
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine().split("=")
            val from = input[0].trim()
            val to = input[1].trim().split(",")
            val left = to[0].trim().replace("(","")
            val right = to[1].trim().replace(")","")
            // println("$from $left $right")

            adjacencyMap[from] = Pair(left, right)
        }
        var steps = 0
        var idx = 0
        var currentNodes = adjacencyMap.filter { it.key.endsWith("A") }.keys
        println(currentNodes.size)
        var allFound = false
        while (!allFound && steps < 10000000) {
            var found = true
            currentNodes = currentNodes.map {
                val node = when(instruction[idx % instruction.size]) {
                    "L" -> {
                        adjacencyMap[it]!!.first
                    }
                    "R" -> {
                        adjacencyMap[it]!!.second
                    }
                    else -> {
                        println("ERROR")
                        exit(1)
                    }
                }
                if (!node.toString().endsWith("Z")) {
                    found = false
                }
                node.toString()
            }.toSet()

            if (found) {
                allFound = true
            }

            idx++
            steps++
        }
        println(steps)
    }
}