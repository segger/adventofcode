package se.johannalynn.adventofcode.y2023

import java.lang.System.exit
import java.math.BigInteger
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
 *
 * 1444534272 too low
 * 2604005936768604160 too high
 * 1185471578816454528
 * 1302002968384302080 too high
 * 11678319315857
 *
 * [15871, 21251, 16409, 11567, 18023, 14257]
 */
object Day8 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(8, false)

        // star1(scanner)
        star2(scanner)

        // val input = Day.file(8, false)
        // star2b(input.lines())
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

    fun star2b(lines: List<String>) {
        val steps = lines.first()

        val map = lines.drop(2).associate { line ->
            val (from, left, right) = """([A-Z]{3}) = \(([A-Z]{3}), ([A-Z]{3})\)""".toRegex().matchEntire(line)!!.groupValues.drop(1)
            from to listOf(left, right)
        }

        val counts = map.keys.filter { it.endsWith("A") }.map { startingPoint ->
            var current = startingPoint
            var count = 0L
            while (!current.endsWith("Z")) {
                 steps.forEach { current = if (it == 'R') map[current]!![1] else map[current]!![0] }
                 count += steps.length
            }
            count
        }
        println(counts)
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
        var steps = mutableListOf<Long>()
        val currentNodes = adjacencyMap.filter { it.key.endsWith("A") }.keys.toList()

        var nodeIdx = 0
        var idx = 0

        var key = currentNodes[nodeIdx]
        while (nodeIdx < currentNodes.size) {
            val node = when(instruction[idx % instruction.size]) {
                "L" -> {
                    adjacencyMap[key]!!.first
                }
                "R" -> {
                    adjacencyMap[key]!!.second
                }
                else -> {
                    println("ERROR")
                    exit(1)
                    ""
                }
            }
            // println("node $node idx $idx instruction ${instruction[idx % instruction.size]}")

            if (node.endsWith("Z")) {
                idx++ // count first node
                steps.add(idx.toLong())
                idx = 0
                if (nodeIdx + 1 < currentNodes.size) {
                    key = currentNodes[++nodeIdx]
                } else {
                    nodeIdx++
                }
            } else {
                key = node
                idx++
            }
        }

        val lcm = steps.reduce { acc, it -> BigInteger(acc.toString()).lcm(BigInteger(it.toString())).toLong() }
        println(lcm)

        //println(steps)
    }

    private fun gcd(a: Long, b: Long): Long {
        var num1 = a
        var num2 = b
        while (num2 > 0L) {
            val temp = b
            num2 = a % b
            num1 = temp
        }
        return num1
    }

    private fun BigInteger.lcm(b: BigInteger): BigInteger {
        return this / this.gcd(b) * b
    }

    private fun List<BigInteger>.lcm(): BigInteger {
        return reduce { acc, bigInteger -> acc.lcm(bigInteger) }
    }
}