package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*
import kotlin.collections.LinkedHashSet
import kotlin.math.abs
import kotlin.math.pow

object Day11 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(11, false)

        // 0 1 10 99 999

        // star1(scanner)
        // star2(scanner)
        star2_recursive(scanner)
    }

    private fun star1(scanner: Scanner) {

        var stones = 0
        val zeros = mutableListOf<String>()
        val evens = mutableListOf<String>()
        val rest = mutableListOf<String>()
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split(" ")

            next.forEach {
                if (it == "0") {
                    zeros.add(it)
                } else if (it.length % 2 == 0) {
                    evens.add(it)
                } else {
                    rest.add(it)
                }
            }
        }

        val blinks = 25
        for (blink in 0 until blinks) {
            val currentZeros = zeros.toMutableList()
            val currentEvens = evens.toMutableList()
            val currentRest = rest.toMutableList()

            zeros.clear()
            evens.clear()
            rest.clear()

            currentZeros.forEach {
                rest.add("1")
            }

            currentEvens.forEach {
                val left = it.substring(0, it.length/2)
                val right = it.substring(it.length/2).toLong().toString()
                if (left.length % 2 == 0) {
                    evens.add(left)
                } else {
                    rest.add(left)
                }
                if (right == "0") {
                    zeros.add(right)
                } else if (right.length % 2 == 0) {
                    evens.add(right)
                } else {
                    rest.add(right)
                }
            }

            currentRest.forEach {
                val newValue = (it.toLong() * 2024).toString()
                if (newValue.length % 2 == 0) {
                    evens.add(newValue)
                } else {
                    rest.add(newValue)
                }
            }
        }

        stones = zeros.size + evens.size + rest.size
        println("stones $stones")
    }

    private fun star2(scanner: Scanner) {

        var stones = 0L
        val input = mutableListOf<String>()

        // check growth rate?
        // input.add("0")

        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split(" ")
            input.addAll(next)
        }

        var current = mutableMapOf<String, Long>()
        input.forEach {
            if (current[it] == null) {
                current[it] = 1
            } else {
                current[it] = current[it]!!.plus(1)
            }
        }

        val library = mutableMapOf<String, List<String>>()
        val blinks = 75
        for (blink in 0 until blinks) {
            val nextCurrent = mutableMapOf<String, Long>()
            current.forEach {
                val children = calc(library, it.key)
                children.forEach { child ->
                    if (nextCurrent[child] == null) {
                        nextCurrent[child] = 0
                    }
                    nextCurrent[child] = nextCurrent[child]!!.plus(it.value)
                }
            }
            current = nextCurrent
            // println("current ${current.map { it.value }.sum()}")
        }

        stones = current.map { it.value }.sum()
        println("stones $stones")
        // 65601038650482 - too low
    }

    private fun calc(library: MutableMap<String, List<String>>, input: String): List<String> {
        if (library[input] != null) {
            return library[input]!!
        } else if (input == "0") {
            library[input] = mutableListOf("1")
        } else if (input.length % 2 == 0) {
            val left = input.substring(0, input.length/2)
            val right = input.substring(input.length/2).toLong().toString()
            library[input] = mutableListOf(left, right)
        } else {
            library[input] = mutableListOf((input.toLong() * 2024).toString())
        }
        return library[input]!!
    }

    private fun star2_recursive(scanner: Scanner) {
        val input = mutableListOf<String>()

        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split(" ")
            input.addAll(next)
        }

        val stones = recursive(input, 75)
        println(stones)
    }

    private fun recursive(stones: List<String>, blinks: Int): Long {
        if (blinks == 0) {
            return stones.size.toLong()
        }
        var sum = 0L
        for (stone in stones) {
            sum += recursive(calc2(stone), blinks - 1)
        }
        return sum
    }

    private fun calc2(input: String): List<String> {
        if (input == "0") {
            return mutableListOf("1")
        } else if (input.length % 2 == 0) {
            val left = input.substring(0, input.length/2)
            val right = input.substring(input.length/2).toLong().toString()
            return mutableListOf(left, right)
        } else {
            return mutableListOf((input.toLong() * 2024).toString())
        }
    }
}
