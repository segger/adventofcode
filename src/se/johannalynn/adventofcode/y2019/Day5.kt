package se.johannalynn.adventofcode.y2019

import java.util.*

object Day5 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(5, false)

        star1(scanner)
    }

    fun star1(scanner: Scanner) {
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine().split(",").map { it.toInt() }.toMutableList()
            val result = calc(input)
            // print(result)
        }
    }

    fun calc(memory: MutableList<Int>): Int {
        var idx = 0
        val instruction = memory[0].toString().padStart(2, '0')
        var opcode = instruction.substring(instruction.length - 2).toInt()
        var mode = instruction.substring(0, instruction.length - 2)

        while(opcode != 99) {
            // println("opcode: ${opcode}")
            if(opcode == 1 || opcode == 2) {
                mode = mode.padStart(3, '0')
                val param1mode = mode[2].toString().toInt()
                val param2mode = mode[1].toString().toInt()

                var param1 = memory[idx + 1]
                if (param1mode == 0) {
                    param1 = memory[memory[idx + 1]]
                }
                var param2 = memory[idx + 2]
                if (param2mode == 0) {
                    param2 = memory[memory[idx + 2]]
                }

                val param3 = memory[idx + 3] // always mode 0
                if (opcode == 1) {
                    memory[param3] = param1 + param2
                } else {
                    memory[param3] = param1 * param2
                }
                idx += 4
            } else if(opcode == 3 || opcode == 4) {
                mode = mode.padStart(1, '0')
                val param1mode = mode[0].toString().toInt()
                var param1 = memory[idx + 1]
                if (param1mode == 0) {
                    param1 = memory[memory[idx + 1]]
                }
                if (opcode == 3) {
                    memory[memory[idx + 1]] = 1 // always mode 0, air condition input
                } else {
                    val output = param1
                    println("output: ${output}")
                }
                idx += 2
            } else {
                print("Error")
                break
            }

            val newInstruction = memory[idx].toString().padStart(2, '0')
            //println("newInstruction: ${newInstruction}")
            opcode = newInstruction.substring(newInstruction.length - 2).toInt()
            mode = newInstruction.substring(0, newInstruction.length - 2)
        }
        return memory[0]
    }
}