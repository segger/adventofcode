package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*
import kotlin.math.abs

object Day9 {

    @JvmStatic
    fun main(args: Array<String>) {
        val usePre = false
        val scanner = start(9, usePre)

        val preamble = if (usePre) 5 else 25
        val number = if (usePre) 127L else 248131121L
        // star1(scanner, preamble)
        star2(scanner, number)
    }

    private fun star1(scanner: Scanner, preamble: Int) {
        val numbers = mutableListOf<Int>()
        var idxStart = 0
        var idxStop = 0
        var found = false
        while (scanner.hasNextLine() && !found) {
            val input = Integer.parseInt(scanner.nextLine())
            // println("### TRY TO ADD $input")

            if (idxStop < preamble) {
                numbers.add(input)
            } else {
                var idxTmp = idxStop - 1
                var valid = false
                while(idxTmp > idxStart && !valid) {
                    val value = numbers[idxTmp]
                    // println("idxTmp $idxTmp with value: $value")
                    val paired = abs(value - input)
                    // println("paired: $paired")
                    for (pairIdx in idxStart .. idxTmp ) {
                        // println("pairIdx: $pairIdx")
                        if (numbers[pairIdx] == paired) {
                            numbers.add(input)
                            // println("ADDED: $input")
                            valid = true
                            break
                        }
                    }
                    idxTmp--
                }

                if (!valid) {
                    found = true
                    println("### FOUND: $input")
                }
            }

            idxStop++
            // println("$idxStart - $idxStop")
            if (idxStop - idxStart > preamble) {
                idxStart++
            }
            // println("$idxStart - $idxStop")
        }
    }

    private fun star2(scanner: Scanner, number: Long) {
        // println("number $number")
        val numbers = mutableListOf<Long>()
        var found = false
        var idxStart = 0
        var idxStop = 0
        var sum = 0L
        while (scanner.hasNextLine() && !found) {
            val input = scanner.nextLine().toLong()
            numbers.add(input)
            idxStop++

            sum += input
            // println("$input -> $sum")

            while (sum >= number && idxStart < idxStop) {
                sum -= numbers[idxStart]
                // println("${numbers[idxStart]} -- $sum")
                idxStart++
                // println("$idxStart $idxStop")
                if (sum == number) {
                    found = true
                    break
                }
            }
            if (sum == number) {
                found = true
            }
        }

        val range = numbers.subList(idxStart, idxStop)
        val min = range.min()!!
        val max = range.max()!!
        // println("min $min max $max")
        val tot = min + max
        println(tot)
    }
}