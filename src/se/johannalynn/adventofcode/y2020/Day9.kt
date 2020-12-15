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
        star1(scanner, preamble)
        // star2(scanner)
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

    private fun star2(scanner: Scanner) {

    }
}