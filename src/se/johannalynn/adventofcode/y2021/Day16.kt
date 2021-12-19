package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.Integer.*
import java.lang.Math.abs
import java.util.*

object Day16 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(16, true)

        // star1(scanner)
        star2(scanner)
    }

    private fun hexToBin(input: String): String {
        return input.chunked(1).map {
            when(it) {
                "0" -> "0000"
                "1" -> "0001"
                "2" -> "0010"
                "3" -> "0011"
                "4" -> "0100"
                "5" -> "0101"
                "6" -> "0110"
                "7" -> "0111"
                "8" -> "1000"
                "9" -> "1001"
                "A" -> "1010"
                "B" -> "1011"
                "C" -> "1100"
                "D" -> "1101"
                "E" -> "1110"
                "F" -> "1111"
                else -> ""
            }
        }.joinToString("")
    }

    /**
     * shl(bits) – signed shift left (Java's <<)
    shr(bits) – signed shift right (Java's >>)
     */
    private fun binToDec(binary: String): Int {
        return binary.chunked(1).asReversed().mapIndexed { idx, nbr ->
            nbr.toInt() shl(idx)
        }.sum()
    }

    private fun star2(scanner: Scanner) {
        val input = scanner.nextLine()
        val binaryStr = hexToBin(input)

    }

    private val versions = mutableListOf<Int>()

    private fun star1(scanner: Scanner) {
        val input = scanner.nextLine()
        val binaryStr = hexToBin(input)
        handlePackage(binaryStr, 0)
        println("sum: ${versions.sum()}")
    }

    private fun handlePackage(str: String, startPointer: Int): Int {
        var pointer = startPointer
        val version = binToDec(str.substring(pointer, pointer+3))
        versions.add(version)
        pointer += 3
        val packetId = binToDec(str.substring(pointer, pointer+3))
        pointer += 3

        // println("version $version, packetId $packetId")

        when (packetId) {
            4 -> return literal(str, pointer)
            else -> return operator(str, pointer)
        }
    }

    private fun literal(str: String, startPointer: Int): Int {
        var pointer = startPointer
        val value = StringBuilder()
        var first = str.substring(pointer, pointer+1)
        pointer += 1
        while (first == "1") {

            val partValue = str.substring(pointer, pointer+4)
            pointer += 4
            value.append(partValue)

            first = str.substring(pointer, pointer+1)
            pointer += 1
        }

        val lastPartValue = str.substring(pointer, pointer+4)
        pointer += 4
        value.append(lastPartValue)
        val decValue = binToDec(value.toString())
        // println("literal value $decValue")
        return pointer
    }

    private fun operator(str: String, startPointer: Int): Int {
        var pointer = startPointer
        val lengthId = binToDec(str.substring(pointer, pointer+1))
        pointer += 1
        if (lengthId == 0) {
            var length = binToDec(str.substring(pointer, pointer+15))
            pointer += 15
            var start = pointer
            pointer = handlePackage(str, pointer)
            while (length - (pointer - start) > 0) {
                length -= (pointer-start)
                start = pointer
                pointer = handlePackage(str, pointer)
            }
        } else {
            val nbrOfPackages = binToDec(str.substring(pointer, pointer+11))
            pointer += 11
            for (nbr in 1..nbrOfPackages) {
                pointer = handlePackage(str, pointer)
            }
        }
        return pointer
    }
}