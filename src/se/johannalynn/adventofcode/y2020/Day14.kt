package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day14 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(14, false)

        star1(scanner)
        // star2(scanner)
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

    private fun star2(scanner: Scanner) {

    }
}