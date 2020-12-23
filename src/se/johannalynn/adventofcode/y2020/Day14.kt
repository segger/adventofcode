package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day14 {

    /*
    mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
mem[8] = 11
mem[7] = 101
mem[8] = 0
     */

    /*
    mask = 000000000000000000000000000000X1001X
mem[42] = 100
mask = 00000000000000000000000000000000X0XX
mem[26] = 1
     */

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(14, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        // 219560887150 -- too low
        val mem = mutableMapOf<Int, CharArray>()
        var mask = ""
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine().split(" = ")
            if(input[0] == "mask") {
                mask = input[1]
            } else {
                val matches = "\\d+".toRegex().find(input[0])
                val address = Integer.parseInt(matches?.value)
                // println(address)

                val decimalValue = Integer.parseInt(input[1])
                val binaryValueTmp = Integer.toBinaryString(decimalValue)
                // println(binaryValueTmp)
                val binaryValue = String.format("%36s", binaryValueTmp).replace(' ', '0').toCharArray()
                // println(binaryValue)

                var result = mem[address]
                if (result == null) {
                    result = "000000000000000000000000000000000000".toCharArray()
                }

                result = binaryValue

                mask.forEachIndexed { idx, it ->
                    when(it) {
                        'X' -> result[idx] = binaryValue[idx]
                        '1' -> result[idx] = '1'
                        '0' -> result[idx] = '0'
                    }
                }

                // println(result)
                mem[address] = result
            }
        }
        // println("mem size: ${mem.size}")
        printSum(mem)
    }

    private fun printSum(mem: Map<Int, CharArray>) {
        val sum = mem.map {
            // println("${it.key}")
            var decimalValue = 0L
            /*it.value.forEach {c ->
                print(c)
            }
            println() */
            it.value.reversed().forEachIndexed { idx, c ->
                val value = if(c == '1') Math.pow(2.0, idx.toDouble()).toLong() else 0
                decimalValue += value
            }
            // println("$decimalValue")
            decimalValue
        }.sum()
        println(sum)
    }

    private fun makeBinary(decimalValue: Int): CharArray {
        val binaryValueTmp = Integer.toBinaryString(decimalValue)
        val binaryValue = String.format("%36s", binaryValueTmp).replace(' ', '0').toCharArray()
        return binaryValue
    }

    private fun star2(scanner: Scanner) {
        // 2262586671901 -- too low
        val mem = mutableMapOf<Long, Long>()
        var mask = ""
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine().split(" = ")
            if(input[0] == "mask") {
                mask = input[1]
            } else {
                val matches = "\\d+".toRegex().find(input[0])
                val address = Integer.parseInt(matches?.value)
                val binaryAddress = makeBinary(address)

                val decimalValue = Integer.parseInt(input[1])

                val defaultAddress = "000000000000000000000000000000000000".toCharArray()
                val idx = binaryAddress.size - 1
                val binaryAddresses = rec(idx, mask, binaryAddress, defaultAddress)
                binaryAddresses.forEach {address ->

                    var decimalAddressValue = 0L
                    address.reversed().forEachIndexed { idx, c ->
                        val value = if(c == '1') Math.pow(2.0, idx.toDouble()).toLong() else 0
                        decimalAddressValue += value
                    }

                    mem[decimalAddressValue] = decimalValue.toLong()
                }
            }
        }
        // println("mem size: ${mem.size}")
        printSum2(mem)
    }

    private fun printSum2(mem: Map<Long, Long>) {
        val sum = mem.map {
            /*
            println("${it.value}")
            it.key.forEach {c ->
                print(c)
            }
            println()*/
            it.value
        }.sum()
        println(sum)
    }

    private fun rec(startIdx: Int, mask: String, address: CharArray, currentAddress: CharArray): List<CharArray> {
        var idx = startIdx
        val result = mutableListOf<CharArray>()
        while(idx >= 0) {
            val maskValue = mask[idx]
            when(maskValue) {
                '1' -> currentAddress[idx] = '1'
                '0' -> currentAddress[idx] = address[idx]
                'X' -> {
                    val copy = currentAddress.copyOf()
                    copy[idx] = '1'
                    val tmp = rec(idx - 1, mask, address, copy)
                    result.addAll(tmp)
                    currentAddress[idx] = '0'
                }
            }
            idx--
        }
        val realCopy = currentAddress.copyOf()
        result.add(realCopy)
        return result
    }

}