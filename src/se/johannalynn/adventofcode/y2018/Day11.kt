package se.johannalynn.adventofcode.y2018

object Day11 {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Day 11")

        val input = 2568
        //val input = 18 //90,269,16
        //val input = 42 //232,251,12
        star2(input)
    }

    private fun star2(input: Int) {
        val squareSize = 300

        val grid = Array(squareSize+1) { IntArray(squareSize+1)}
        for (i in 1..squareSize) {
            for(j in 1..squareSize) {
                val powerLevel = calc(i, j, input)
                grid[i][j] = powerLevel
                //print(" " + grid[i][j])
            }
            //println()
        }

        var max = Int.MIN_VALUE
        var max_x = 0;
        var max_y = 0;
        var maxInputSize = 0;

        for (i in 1..squareSize-2) {
            for (j in 1..squareSize-2) {

                //println("i=$i, j=$j")

                var square = 0
                val value = grid[i][j] + grid[i][j + 1]+grid[i + 1][j] + grid[i + 1][j + 1]

                var size = 2
                square += value
                //println("size=$size, square=$square")

                while (i + size <= squareSize && j + size <= squareSize) {
                    for (k in 0..size-1) {
                        square += grid[i + size][j + k]
                        //println("-> square=$square")
                        square += grid[i + k][j + size]
                        //println("--> square=$square")
                    }
                    square += grid[i+size][j+size]

                    //println("=> size-1=$size, square=$square")
                    if (square > max) {
                        max = square
                        max_x = i
                        max_y = j
                        maxInputSize = size+1
                    }
                    size++
                }
            }
        }


        println("$max for ($max_x,$max_y,$maxInputSize)")
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