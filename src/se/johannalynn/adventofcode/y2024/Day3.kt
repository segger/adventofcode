package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*
import kotlin.math.abs

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
            val next = scanner.nextLine()
            "mul\\([0-9]{1,3},[0-9]{1,3}\\)".toRegex().findAll(next).forEach { mul ->
                var prod = 1
                "[0-9]{1,3}".toRegex().findAll(mul.value).forEach {
                    prod *=it.value.toInt()
                }
                sum += prod
            }
        }
        println(sum)
    }

    private fun star2(scanner: Scanner) {

        var sum = 0
        var enabled = true
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            "do\\(\\)|don't\\(\\)|mul\\([0-9]{1,3},[0-9]{1,3}\\)".toRegex().findAll(next).forEach { mul ->
                if (mul.value.startsWith("don")) {
                    enabled = false
                } else if (mul.value.startsWith("do")) {
                    enabled = true
                } else {
                    var prod = 1
                    "[0-9]{1,3}".toRegex().findAll(mul.value).forEach {
                        prod *=it.value.toInt()
                    }
                    if (enabled) {
                        sum += prod
                    }
                }
            }
        }
        println(sum)
    }
}
