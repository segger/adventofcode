package se.johannalynn.adventofcode.y2019

import java.util.*

object Day7 {

    /**
     *43210 (from phase setting sequence 4,3,2,1,0)
     *3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0
     *
     * 54321 (from phase setting sequence 0,1,2,3,4):
     * 3,23,3,24,1002,24,10,24,1002,23,-1,23,
    101,5,23,23,1,24,23,23,4,23,99,0,0

    65210 (from phase setting sequence 1,0,4,3,2)
    3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,
    1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0
     */

    /**
     *139629729 (from phase setting sequence 9,8,7,6,5)
     * 3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,
    27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5

    18216 (from phase setting sequence 9,7,8,5,6)
    3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,
    -5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,
    53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10
     */

    val settings = arrayOf(
            intArrayOf(0, 1, 2, 3, 4), intArrayOf(0, 1, 2, 4, 3), intArrayOf(0, 1, 3, 2, 4), intArrayOf(0, 1, 3, 4, 2), intArrayOf(0, 1, 4, 3, 2), intArrayOf(0, 1, 4, 2, 3),
            intArrayOf(0, 2, 1, 3, 4), intArrayOf(0, 2, 1, 4, 3), intArrayOf(0, 2, 3, 4, 1), intArrayOf(0, 2, 3, 1, 4), intArrayOf(0, 2, 4, 3, 1), intArrayOf(0, 2, 4, 1, 3),
            intArrayOf(0, 3, 1, 2, 4), intArrayOf(0, 3, 1, 4, 2), intArrayOf(0, 3, 2, 4, 1), intArrayOf(0, 3, 2, 1, 4), intArrayOf(0, 3, 4, 2, 1), intArrayOf(0, 3, 4, 1, 2),
            intArrayOf(0, 4, 1, 2, 3), intArrayOf(0, 4, 1, 3, 2), intArrayOf(0, 4, 2, 1, 3), intArrayOf(0, 4, 2, 3, 1), intArrayOf(0, 4, 3, 2, 1), intArrayOf(0, 4, 3, 1, 2),
            intArrayOf(1, 0, 2, 3, 4), intArrayOf(1, 0, 2, 4, 3), intArrayOf(1, 0, 3, 2, 4), intArrayOf(1, 0, 3, 4, 2), intArrayOf(1, 0, 4, 3, 2), intArrayOf(1, 0, 4, 2, 3),
            intArrayOf(1, 2, 0, 3, 4), intArrayOf(1, 2, 0, 4, 3), intArrayOf(1, 2, 3, 4, 0), intArrayOf(1, 2, 3, 0, 4), intArrayOf(1, 2, 4, 3, 0), intArrayOf(1, 2, 4, 0, 3),
            intArrayOf(1, 3, 0, 2, 4), intArrayOf(1, 3, 0, 4, 2), intArrayOf(1, 3, 2, 4, 0), intArrayOf(1, 3, 2, 0, 4), intArrayOf(1, 3, 4, 2, 0), intArrayOf(1, 3, 4, 0, 2),
            intArrayOf(1, 4, 0, 2, 3), intArrayOf(1, 4, 0, 3, 2), intArrayOf(1, 4, 2, 0, 3), intArrayOf(1, 4, 2, 3, 0), intArrayOf(1, 4, 3, 2, 0), intArrayOf(1, 4, 3, 0, 2),
            intArrayOf(2, 1, 0, 3, 4), intArrayOf(2, 1, 0, 4, 3), intArrayOf(2, 1, 3, 0, 4), intArrayOf(2, 1, 3, 4, 0), intArrayOf(2, 1, 4, 3, 0), intArrayOf(2, 1, 4, 0, 3),
            intArrayOf(2, 0, 1, 3, 4), intArrayOf(2, 0, 1, 4, 3), intArrayOf(2, 0, 3, 4, 1), intArrayOf(2, 0, 3, 1, 4), intArrayOf(2, 0, 4, 3, 1), intArrayOf(2, 0, 4, 1, 3),
            intArrayOf(2, 3, 1, 0, 4), intArrayOf(2, 3, 1, 4, 0), intArrayOf(2, 3, 0, 4, 1), intArrayOf(2, 3, 0, 1, 4), intArrayOf(2, 3, 4, 0, 1), intArrayOf(2, 3, 4, 1, 0),
            intArrayOf(2, 4, 1, 0, 3), intArrayOf(2, 4, 1, 3, 0), intArrayOf(2, 4, 0, 1, 3), intArrayOf(2, 4, 0, 3, 1), intArrayOf(2, 4, 3, 0, 1), intArrayOf(2, 4, 3, 1, 0),
            intArrayOf(3, 1, 2, 0, 4), intArrayOf(3, 1, 2, 4, 0), intArrayOf(3, 1, 0, 2, 4), intArrayOf(3, 1, 0, 4, 2), intArrayOf(3, 1, 4, 0, 2), intArrayOf(3, 1, 4, 2, 0),
            intArrayOf(3, 2, 1, 0, 4), intArrayOf(3, 2, 1, 4, 0), intArrayOf(3, 2, 0, 4, 1), intArrayOf(3, 2, 0, 1, 4), intArrayOf(3, 2, 4, 0, 1), intArrayOf(3, 2, 4, 1, 0),
            intArrayOf(3, 0, 1, 2, 4), intArrayOf(3, 0, 1, 4, 2), intArrayOf(3, 0, 2, 4, 1), intArrayOf(3, 0, 2, 1, 4), intArrayOf(3, 0, 4, 2, 1), intArrayOf(3, 0, 4, 1, 2),
            intArrayOf(3, 4, 1, 2, 0), intArrayOf(3, 4, 1, 0, 2), intArrayOf(3, 4, 2, 1, 0), intArrayOf(3, 4, 2, 0, 1), intArrayOf(3, 4, 0, 2, 1), intArrayOf(3, 4, 0, 1, 2),
            intArrayOf(4, 1, 2, 3, 0), intArrayOf(4, 1, 2, 0, 3), intArrayOf(4, 1, 3, 2, 0), intArrayOf(4, 1, 3, 0, 2), intArrayOf(4, 1, 0, 3, 2), intArrayOf(4, 1, 0, 2, 3),
            intArrayOf(4, 2, 1, 3, 0), intArrayOf(4, 2, 1, 0, 3), intArrayOf(4, 2, 3, 0, 1), intArrayOf(4, 2, 3, 1, 0), intArrayOf(4, 2, 0, 3, 1), intArrayOf(4, 2, 0, 1, 3),
            intArrayOf(4, 3, 1, 2, 0), intArrayOf(4, 3, 1, 0, 2), intArrayOf(4, 3, 2, 0, 1), intArrayOf(4, 3, 2, 1, 0), intArrayOf(4, 3, 0, 2, 1), intArrayOf(4, 3, 0, 1, 2),
            intArrayOf(4, 0, 1, 2, 3), intArrayOf(4, 0, 1, 3, 2), intArrayOf(4, 0, 2, 1, 3), intArrayOf(4, 0, 2, 3, 1), intArrayOf(4, 0, 3, 2, 1), intArrayOf(4, 0, 3, 1, 2)
            )

    val feedbackSettings = arrayOf(
            intArrayOf(5, 6, 7, 8, 9), intArrayOf(5, 6, 7, 9, 8), intArrayOf(5, 6, 8, 7, 9), intArrayOf(5, 6, 8, 9, 7), intArrayOf(5, 6, 9, 8, 7), intArrayOf(5, 6, 9, 7, 8),
            intArrayOf(5, 7, 6, 8, 9), intArrayOf(5, 7, 6, 9, 8), intArrayOf(5, 7, 8, 9, 6), intArrayOf(5, 7, 8, 6, 9), intArrayOf(5, 7, 9, 8, 6), intArrayOf(5, 7, 9, 6, 8),
            intArrayOf(5, 8, 6, 7, 9), intArrayOf(5, 8, 6, 9, 7), intArrayOf(5, 8, 7, 9, 6), intArrayOf(5, 8, 7, 6, 9), intArrayOf(5, 8, 9, 7, 6), intArrayOf(5, 8, 9, 6, 7),
            intArrayOf(5, 9, 6, 7, 8), intArrayOf(5, 9, 6, 8, 7), intArrayOf(5, 9, 7, 6, 8), intArrayOf(5, 9, 7, 8, 6), intArrayOf(5, 9, 8, 7, 6), intArrayOf(5, 9, 8, 6, 7),
            intArrayOf(6, 5, 7, 8, 9), intArrayOf(6, 5, 7, 9, 8), intArrayOf(6, 5, 8, 7, 9), intArrayOf(6, 5, 8, 9, 7), intArrayOf(6, 5, 9, 8, 7), intArrayOf(6, 5, 9, 7, 8),
            intArrayOf(6, 7, 5, 8, 9), intArrayOf(6, 7, 5, 9, 8), intArrayOf(6, 7, 8, 9, 5), intArrayOf(6, 7, 8, 5, 9), intArrayOf(6, 7, 9, 8, 5), intArrayOf(6, 7, 9, 5, 8),
            intArrayOf(6, 8, 5, 7, 9), intArrayOf(6, 8, 5, 9, 7), intArrayOf(6, 8, 7, 9, 5), intArrayOf(6, 8, 7, 5, 9), intArrayOf(6, 8, 9, 7, 5), intArrayOf(6, 8, 9, 5, 7),
            intArrayOf(6, 9, 5, 7, 8), intArrayOf(6, 9, 5, 8, 7), intArrayOf(6, 9, 7, 5, 8), intArrayOf(6, 9, 7, 8, 5), intArrayOf(6, 9, 8, 7, 5), intArrayOf(6, 9, 8, 5, 7),
            intArrayOf(7, 6, 5, 8, 9), intArrayOf(7, 6, 5, 9, 8), intArrayOf(7, 6, 8, 5, 9), intArrayOf(7, 6, 8, 9, 5), intArrayOf(7, 6, 9, 8, 5), intArrayOf(7, 6, 9, 5, 8),
            intArrayOf(7, 5, 6, 8, 9), intArrayOf(7, 5, 6, 9, 8), intArrayOf(7, 5, 8, 9, 6), intArrayOf(7, 5, 8, 6, 9), intArrayOf(7, 5, 9, 8, 6), intArrayOf(7, 5, 9, 6, 8),
            intArrayOf(7, 8, 6, 5, 9), intArrayOf(7, 8, 6, 9, 5), intArrayOf(7, 8, 5, 9, 6), intArrayOf(7, 8, 5, 6, 9), intArrayOf(7, 8, 9, 5, 6), intArrayOf(7, 8, 9, 6, 5),
            intArrayOf(7, 9, 6, 5, 8), intArrayOf(7, 9, 6, 8, 5), intArrayOf(7, 9, 5, 6, 8), intArrayOf(7, 9, 5, 8, 6), intArrayOf(7, 9, 8, 5, 6), intArrayOf(7, 9, 8, 6, 5),
            intArrayOf(8, 6, 7, 5, 9), intArrayOf(8, 6, 7, 9, 5), intArrayOf(8, 6, 5, 7, 9), intArrayOf(8, 6, 5, 9, 7), intArrayOf(8, 6, 9, 5, 7), intArrayOf(8, 6, 9, 7, 5),
            intArrayOf(8, 7, 6, 5, 9), intArrayOf(8, 7, 6, 9, 5), intArrayOf(8, 7, 5, 9, 6), intArrayOf(8, 7, 5, 6, 9), intArrayOf(8, 7, 9, 5, 6), intArrayOf(8, 7, 9, 6, 5),
            intArrayOf(8, 5, 6, 7, 9), intArrayOf(8, 5, 6, 9, 7), intArrayOf(8, 5, 7, 9, 6), intArrayOf(8, 5, 7, 6, 9), intArrayOf(8, 5, 9, 7, 6), intArrayOf(8, 5, 9, 6, 7),
            intArrayOf(8, 9, 6, 7, 5), intArrayOf(8, 9, 6, 5, 7), intArrayOf(8, 9, 7, 6, 5), intArrayOf(8, 9, 7, 5, 6), intArrayOf(8, 9, 5, 7, 6), intArrayOf(8, 9, 5, 6, 7),
            intArrayOf(9, 6, 7, 8, 5), intArrayOf(9, 6, 7, 5, 8), intArrayOf(9, 6, 8, 7, 5), intArrayOf(9, 6, 8, 5, 7), intArrayOf(9, 6, 5, 8, 7), intArrayOf(9, 6, 5, 7, 8),
            intArrayOf(9, 7, 6, 8, 5), intArrayOf(9, 7, 6, 5, 8), intArrayOf(9, 7, 8, 5, 6), intArrayOf(9, 7, 8, 6, 5), intArrayOf(9, 7, 5, 8, 6), intArrayOf(9, 7, 5, 6, 8),
            intArrayOf(9, 8, 6, 7, 5), intArrayOf(9, 8, 6, 5, 7), intArrayOf(9, 8, 7, 5, 6), intArrayOf(9, 8, 7, 6, 5), intArrayOf(9, 8, 5, 7, 6), intArrayOf(9, 8, 5, 6, 7),
            intArrayOf(9, 5, 6, 7, 8), intArrayOf(9, 5, 6, 8, 7), intArrayOf(9, 5, 7, 6, 8), intArrayOf(9, 5, 7, 8, 6), intArrayOf(9, 5, 8, 7, 6), intArrayOf(9, 5, 8, 6, 7)
    )

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(7, false)

        // star1(scanner)
        // too high: 49783
        star2(scanner)
    }

    fun star2(scanner: Scanner) {
        while(scanner.hasNextLine()) {
            val program = scanner.nextLine().split(",").map { it.toInt() }

            var maxOutput = Int.MIN_VALUE
            var maxSettings: IntArray = intArrayOf(0, 0, 0, 0, 0)
            feedbackSettings.forEach {
                val a = Intcode(program.toMutableList(), it[0], "a")
                val b = Intcode(program.toMutableList(), it[1], "b")
                val c = Intcode(program.toMutableList(), it[2], "c")
                val d = Intcode(program.toMutableList(), it[3], "d")
                val e = Intcode(program.toMutableList(), it[4], "e")

                val computers = listOf(a, b, c, d, e)
                var input = 0
                try {
                    while(true) {
                        for (amp in 0..4) {
                            val output = computers[amp].amp(input)
                            input = output
                        }
                    }
                } catch (ex: DoneException) {
                }

                if (input > maxOutput) {
                    maxOutput = input
                    maxSettings = it
                }
            }

            println(maxOutput)
            maxSettings.forEach { print("${it},") }
        }
    }

    fun star1(scanner: Scanner) {
        while(scanner.hasNextLine()) {
            val program = scanner.nextLine().split(",").map { it.toInt() }.toMutableList()

            var maxOutput = Int.MIN_VALUE
            var maxSettings: IntArray = intArrayOf(0, 0, 0, 0, 0)
            settings.forEach {
                var input = 0
                for(amp in 0..4) {
                    val output = day7(program, it[amp], input)
                    // println(output)
                    input = output
                }
                // println(input)

                if (input > maxOutput) {
                    maxOutput = input
                    maxSettings = it
                }
            }

            println(maxOutput)
            maxSettings.forEach { print("${it}, ") }
        }
    }

    fun day7(memory: MutableList<Int>, phase: Int, input: Int): Int {
        var setPhase = true
        var idx = 0
        val instruction = memory[0].toString().padStart(2, '0')
        var opcode = instruction.substring(instruction.length - 2).toInt()
        var mode = instruction.substring(0, instruction.length - 2)
        //var count = 0
        while(opcode != 99) {
            //count++
            //println("opcode: ${opcode}")
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
                if (opcode == 3 && setPhase) {
                    memory[memory[idx + 1]] = phase // always mode 0
                    setPhase = false
                } else if (opcode == 3 && !setPhase) {
                    memory[memory[idx + 1]] = input // always mode 0
                } else {
                    // val output = param1
                    // println("output: ${output}")
                    return param1
                }
                idx += 2
            } else if (opcode == 5 || opcode == 6) {
                mode = mode.padStart(2, '0')
                val param1mode = mode[1].toString().toInt()
                var param1 = memory[idx + 1]
                if (param1mode == 0) {
                    param1 = memory[memory[idx + 1]]
                }
                val param2mode = mode[0].toString().toInt()
                var param2 = memory[idx + 2]
                if (param2mode == 0) {
                    param2 = memory[memory[idx + 2]]
                }
                if (opcode == 5) {
                    if (param1 != 0) {
                        idx = param2
                    } else {
                        idx += 3
                    }
                }
                if (opcode == 6) {
                    if (param1 == 0) {
                        idx = param2
                    } else {
                        idx += 3
                    }
                }
            } else if (opcode == 7 || opcode == 8) {
                mode = mode.padStart(3, '0')
                val param1mode = mode[2].toString().toInt()
                var param1 = memory[idx + 1]
                if (param1mode == 0) {
                    param1 = memory[memory[idx + 1]]
                }
                val param2mode = mode[1].toString().toInt()
                var param2 = memory[idx + 2]
                if (param2mode == 0) {
                    param2 = memory[memory[idx + 2]]
                }
                val param3 = memory[idx + 3]
                if (opcode == 7) {
                    memory[param3] = if (param1 < param2) 1 else 0
                }
                if (opcode == 8) {
                    memory[param3] = if (param1 == param2) 1 else 0
                }
                idx += 4
            } else {
                print("Error")
                break
            }

            val newInstruction = memory[idx].toString().padStart(2, '0')
            //println("newInstruction: ${newInstruction}")
            opcode = newInstruction.substring(newInstruction.length - 2).toInt()
            mode = newInstruction.substring(0, newInstruction.length - 2)
        }
        print("Exit code")
        return -1
    }
}