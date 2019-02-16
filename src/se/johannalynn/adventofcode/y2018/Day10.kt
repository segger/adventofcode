package se.johannalynn.adventofcode.y2018

import java.util.*
import kotlin.collections.ArrayList

object Day10 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(10, false)

        star1(scanner)
    }

    class PointInTheSky(val x: Int, val y: Int, val v_x: Int, val v_y: Int) {
        fun atSecond(sec: Int): IntArray {
            val pos_x = x + sec * v_x
            val pos_y = y + sec * v_y
            return intArrayOf(pos_x, pos_y)
        }
    }

    private fun star1(scanner: Scanner) {
        val sky = ArrayList<PointInTheSky>()
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine()
            val pos = input.substringAfter("position=<").substringBefore(">").trim().split(",")
            val vel = input.substringAfter("velocity=<").substringBefore(">").trim().split(",")
            val x = pos[0].trim().toInt()
            val y = pos[1].trim().toInt()
            val v_x = vel[0].trim().toInt()
            val v_y = vel[1].trim().toInt()

            sky.add(PointInTheSky(x, y, v_x, v_y))
        }

        var sec = 0

        var diff_min = Int.MAX_VALUE
        var diff_min_sec = 0
        var diff_min_x_min = 0
        var diff_min_y_min = 0
        var diff_min_x_max = 0
        var diff_min_y_max = 0

        while(sec < 20000) {
            var x_min = Int.MAX_VALUE
            var x_max = Int.MIN_VALUE
            var y_min = Int.MAX_VALUE
            var y_max = Int.MIN_VALUE

            for(point in sky) {

                val pos = point.atSecond(sec)

                if(pos[0] > x_max) {
                    x_max = pos[0]
                }
                if(pos[0] < x_min) {
                    x_min = pos[0]
                }
                if(pos[1] > y_max) {
                    y_max = pos[1]
                }
                if(pos[1] < y_min) {
                    y_min = pos[1]
                }
            }

            val diff = x_max - x_min + y_max - y_min

            if(diff < diff_min) {
                diff_min = diff
                diff_min_sec = sec
                diff_min_x_max = x_max
                diff_min_y_max = y_max
                diff_min_x_min = x_min
                diff_min_y_min = y_min
            }
            sec++
        }

        println("sec=$diff_min_sec")
        val diff_x = diff_min_x_max - diff_min_x_min
        val diff_y = diff_min_y_max - diff_min_y_min

        val printSky = Array(diff_y+1) { arrayOfNulls<String>(diff_x+1) }
        for (point in sky) {
            val pos = point.atSecond(diff_min_sec)
            val posX = pos[0] - Math.abs(diff_min_x_min)
            val posY = pos[1] - Math.abs(diff_min_y_min)
            printSky[posY][posX] = "#"
        }

        //println(Arrays.deepToString(printSky))

        for(row in printSky) {
            for(point in row) {
                val star = point?.let { "#" } ?: "."
                print("$star ")
            }
            println()
        }

    }
}