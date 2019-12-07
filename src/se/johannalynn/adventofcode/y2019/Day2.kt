package se.johannalynn.adventofcode.y2019

import java.util.*

object Day2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(2, false)

        //star1(scanner)
        star2(scanner)
    }

    fun star1(scanner: Scanner) {
        while(scanner.hasNextLine()) {
            val next = scanner.nextLine().split(",").map { it.toInt() }.toMutableList()
            var idx = 0
            var start = next[idx]
            while(start != 99) {
                if(start == 1) {
                    next[next[idx + 3]] = next[next[idx + 1]] + next[next[idx + 2]]
                } else if(start == 2) {
                    next[next[idx + 3]] = next[next[idx + 1]] * next[next[idx + 2]]
                } else {
                    print("Error")
                    break
                }
                idx += 4
                start = next[idx]
            }
            print(next[0])
        }
    }

    fun calc(memory: MutableList<Int>): Int {
        var idx = 0
        var start = memory[idx]
        while(start != 99) {
            if(start == 1) {
                memory[memory[idx + 3]] = memory[memory[idx + 1]] + memory[memory[idx + 2]]
            } else if(start == 2) {
                memory[memory[idx + 3]] = memory[memory[idx + 1]] * memory[memory[idx + 2]]
            } else {
                print("Error")
                break
            }
            idx += 4
            start = memory[idx]
        }
        return memory[0]
    }

    fun star2(scanner: Scanner) {
        // 19690720

        while(scanner.hasNextLine()) {
            val input = scanner.nextLine().split(",").map{ it.toInt() }

            var k = 0
            while (k < 100) {
                var p = 0
                while (p < 100) {
                    val memory = input.toMutableList()
                    val noun = k
                    val verb = p
                    memory[1] = noun
                    memory[2] = verb
                    val result = calc(memory)

                    if(result == 19690720) {
                        println(100 * noun + verb)
                        return
                    }
                    p++
                }
                k++
            }
        }
    }
}