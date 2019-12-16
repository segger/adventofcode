package se.johannalynn.adventofcode.y2019

import java.lang.RuntimeException
import java.math.BigInteger

class Intcode2(val memory: MutableList<BigInteger>) {

    var idx = 0
    var relativeBase = 0

    fun emem(idx: Int): Int {
        //println("${idx} >= ${memory.size}")
        while(idx >= memory.size) {
            memory.addAll(List((memory.size), { BigInteger.ZERO }))
        }
        return idx
    }

    fun param1(mode: Int): BigInteger {
        val param1idx = idx+1
        return param(mode, param1idx)
    }

    fun param2(mode: Int): BigInteger {
        val param2idx = idx+2
        return param(mode, param2idx)
    }

    fun writeParam1(mode: Int): Int {
        val param1idx = idx+1
        return writeParam(mode, param1idx)
    }

    fun writeParam3(mode: Int): Int {
        val param3idx = idx+3
        return writeParam(mode, param3idx)
    }

    fun writeParam(mode: Int, paramIdx: Int): Int {
        var param3 = memory[emem(paramIdx)].toInt()
        if (mode == 2) {
            param3 = relativeBase + memory[emem(paramIdx)].toInt()
        }
        return param3
    }

    fun param(mode: Int, paramIdx: Int): BigInteger {
        var param = memory[emem(paramIdx)]
        if (mode == 0) {
            param = memory[emem(memory[paramIdx].toInt())]
        } else if(mode == 2) {
            param = memory[emem(relativeBase + emem(memory[paramIdx].toInt()))]
            //param = memory[emem(relativeBase + paramIdx)]
            //param = memory[emem(relativeBase + memory[paramIdx].toInt())]
        }
        return param
    }

    fun boost(input: BigInteger) {
        //println("${name} at ${idx} with ${input}")
        //println("${memory}")
        val instruction = memory[idx].toString().padStart(2, '0')
        var opcode = instruction.substring(instruction.length - 2).toInt()
        var mode = instruction.substring(0, instruction.length - 2)
        //var count = 0
        while(opcode != 99) {
            //count++
            //println("opcode: ${opcode}")
            //println("mode: ${mode}")
            if(opcode == 1 || opcode == 2) {
                mode = mode.padStart(3, '0')
                val param1mode = mode[2].toString().toInt()
                val param2mode = mode[1].toString().toInt()
                val param3mode = mode[0].toString().toInt()

                val param1 = param1(param1mode)
                val param2 = param2(param2mode)
                val param3 = writeParam3(param3mode)

                //val param3 = memory[idx + 3].toInt() // always mode 0
                if (opcode == 1) {
                    memory[emem(param3)] = param1 + param2
                } else {
                    memory[emem(param3)] = param1 * param2
                }
                idx += 4
            } else if(opcode == 3 || opcode == 4) {
                //println("opcode: ${opcode}")
                mode = mode.padStart(1, '0')
                //println("mode: ${mode}")
                val param1mode = mode[0].toString().toInt()
                //val param1 = param1(param1mode)
                //val param1 = writeParam1(param1mode)
                //println("param1: ${param1}")

                if (opcode == 3) {
                    //println("input is ${input}")
                    //memory[emem(memory[idx + 1].toInt())] = input // always mode 0
                    //println(memory)
                    val param1 = writeParam1(param1mode)
                    memory[emem(param1)] = input
                    //println(memory)
                    idx += 2
                } else {
                    val param1 = param1(param1mode)
                    //println("param1: ${param1}")
                    val output = param1
                    //println("output from ${name} is ${output}")
                    //return param1
                    println("output: ${param1}")
                    idx += 2
                }
            } else if (opcode == 5 || opcode == 6) {
                mode = mode.padStart(2, '0')
                val param1mode = mode[1].toString().toInt()
                val param2mode = mode[0].toString().toInt()
                val param1 = param1(param1mode)
                val param2 = param2(param2mode)

                if (opcode == 5) {
                    if (param1.compareTo(BigInteger.ZERO) != 0) {
                        idx = param2.toInt()
                    } else {
                        idx += 3
                    }
                }
                if (opcode == 6) {
                    if (param1.compareTo(BigInteger.ZERO) == 0) {
                        idx = param2.toInt()
                    } else {
                        idx += 3
                    }
                }
            } else if (opcode == 7 || opcode == 8) {
                mode = mode.padStart(3, '0')
                val param1mode = mode[2].toString().toInt()
                val param2mode = mode[1].toString().toInt()
                val param3mode = mode[0].toString().toInt()

                val param1 = param1(param1mode)
                val param2 = param2(param2mode)
                val param3 = writeParam3(param3mode)

                //val param3 = memory[idx + 3].toInt() // always mode 0
                if (opcode == 7) {
                    memory[emem(param3)] = if (param1.compareTo(param2) < 0) BigInteger.ONE else BigInteger.ZERO
                }
                if (opcode == 8) {
                    memory[emem(param3)] = if (param1.compareTo(param2) == 0) BigInteger.ONE else BigInteger.ZERO
                }
                idx += 4
            } else if (opcode == 9) {
                //println("mode: ${mode}, idx: ${idx+1}")

                mode = mode.padStart(1, '0')
                val param1mode = mode[0].toString().toInt()

                val param1 = param1(param1mode)
                //val param1 = writeParam1(param1mode)
                //println("param1: ${param1}")
                //println("relativeBase: ${relativeBase}")
                relativeBase += param1.toInt()
                //println("relativeBase: ${relativeBase}")
                idx += 2
            } else {
                print("Error opcode ${opcode}")
                throw RuntimeException()
            }

            val newInstruction = memory[idx].toString().padStart(2, '0')
            //println("newInstruction: ${newInstruction}")
            opcode = newInstruction.substring(newInstruction.length - 2).toInt()
            mode = newInstruction.substring(0, newInstruction.length - 2)
        }
        print("Done")
        println(memory)
        //throw DoneException()
    }
}