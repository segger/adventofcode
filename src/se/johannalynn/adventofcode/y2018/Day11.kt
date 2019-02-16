package se.johannalynn.adventofcode.y2018

object Day11 {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Day 11")

        val input = 2568
        //val input = 42
        star1(input)
    }

    private fun star1(input: Int) {
        val grid = Array(301) { IntArray(301)}
        for (i in 1..300) {
            for(j in 1..300) {
                val powerLevel = calc(i, j, input)
                grid[i][j] = powerLevel
            }
        }

        var max = Int.MIN_VALUE
        var max_x = 0;
        var max_y = 0;
        for (i in 1..300-2) {
            for(j in 1..300-2) {

                var square = 0
                for(m in 0..2) {
                    for(n in 0..2) {
                        val value = grid[i+m][j+n]
                        square += value
                    }
                }
                if (square > max) {
                    max = square
                    max_x = i
                    max_y = j
                }
            }
        }
        println("$max for ($max_x, $max_y)")
    }

    private fun calc(x: Int, y: Int, serial: Int): Int {
        /*val x = 122
        val y = 79
        val serial = 57
        val result = -5*/
        /*
        val x = 217
        val y = 196
        val serial = 39
        val result = 0*/
        /*
        val x = 101
        val y = 153
        val serial = 71
        val result = 4*/
        /*
        val x = 3
        val y = 5
        val serial = 8
        val result = 4*/

        val rackId = x + 10
        //println("rackId=$rackId")
        var powerLevel = rackId * y
        //println("initialPowerLevel=$powerLevel")
        powerLevel += serial
        //println("serialPowerLevel=$powerLevel")
        powerLevel *= rackId
        //println("powerLevel=$powerLevel")
        val hundred = (powerLevel / 100)
        //println("hundred=$hundred")
        var digit = hundred % 10
        //println("digit=$digit")
        digit -= 5

        return digit
    }

}