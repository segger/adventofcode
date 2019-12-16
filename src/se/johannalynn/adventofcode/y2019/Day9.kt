package se.johannalynn.adventofcode.y2019

import java.math.BigInteger
import java.util.*

object Day9 {

    /**
     * 109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99 takes no input and produces a copy of itself as output.
     * 1102,34915192,34915192,7,4,7,99,0 should output a 16-digit number.
     * 104,1125899906842624,99 should output the large number in the middle.
     */

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(9, false)

        //star1(scanner)
        // 203 (too low)
        star2(scanner)
    }

    fun star1(scanner: Scanner) {
        while (scanner.hasNextLine()) {
            val program = scanner.nextLine().split(",").map { it.toBigInteger() }
            val computer = Intcode2(program.toMutableList())
            computer.boost(BigInteger.ONE)
        }
    }

    fun star2(scanner: Scanner) {
        while (scanner.hasNextLine()) {
            val program = scanner.nextLine().split(",").map { it.toBigInteger() }
            val computer = Intcode2(program.toMutableList())
            computer.boost(BigInteger.TWO)
        }
    }
}