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

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(7, false)

        star1(scanner)
        // too high: 49783
    }

    fun star1(scanner: Scanner) {
        var computer = Intcode()
        while(scanner.hasNextLine()) {
            val program = scanner.nextLine().split(",").map { it.toInt() }.toMutableList()

            var maxOutput = Int.MIN_VALUE
            var maxSettings: IntArray = intArrayOf(0, 0, 0, 0, 0)
            settings.forEach {
                var input = 0
                for(amp in 0..4) {
                    val output = computer.day7(program, it[amp], input)
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
}