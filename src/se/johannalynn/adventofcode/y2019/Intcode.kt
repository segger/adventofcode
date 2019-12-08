package se.johannalynn.adventofcode.y2019

class Intcode {

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