package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day8 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(8, false)

        star1(scanner)
        // star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val inputList = mutableListOf<Pair<String, String>>()
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine().split(" ")
            val command = input[0]
            val steps = input[1]
            // println("$command")
            // println("$steps")
            inputList.add(Pair(command, steps))
        }
        var accumulator = 0
        var idx = 0
        var repeated = false
        val executed = mutableSetOf<Int>()
        while(idx < inputList.size && !repeated) {
            val command = inputList[idx]
            // println("command $command")
            val value = Integer.parseInt(command.second.substring(1))
            var add = false
            if (command.second[0] == '+') {
                add = true
            }
            when(command.first) {
                "nop" -> idx++
                "acc" -> {
                    if (add) accumulator += value else accumulator -= value
                    idx++
                }
                "jmp" -> if (add) idx += value else idx -= value
            }
            if(!executed.add(idx)) {
                repeated = true
            }
        }
        println(accumulator)
    }

    private fun star2(scanner: Scanner) {

    }
}