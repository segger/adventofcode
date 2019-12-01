package se.johannalynn.adventofcode.y2019

import se.johannalynn.adventofcode.y2019.Day.start
import java.math.BigInteger
import java.util.*

object Day1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(1, true)

        //star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var tot = BigInteger.ZERO
        while (scanner.hasNextLine()) {
            val moduleMass = BigInteger(scanner.nextLine())
            val div = moduleMass.div(BigInteger("3"))
            val fuel = div.subtract(BigInteger("2"))
            tot = tot.add(fuel)
        }
        println(tot)
    }

    private fun star2(scanner: Scanner) {
        var tot = BigInteger.ZERO
        while (scanner.hasNextLine()) {
            val moduleMass = BigInteger(scanner.nextLine())
            val fuel = moreFuel(moduleMass)
            tot = tot.add(fuel)
        }
        println(tot)
    }

    private fun moreFuel(input: BigInteger): BigInteger {
        val div = input.div(BigInteger("3"))
        val fuel = div.subtract(BigInteger("2"))
        if (fuel.compareTo(BigInteger.ZERO) <= 0) {
            return BigInteger.ZERO
        } else {
            return fuel + moreFuel(fuel)
        }
    }
}