package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.Integer.max
import java.lang.Integer.min
import java.util.*

object Day5 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(5, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {

        val points = mutableMapOf<Pair<Int, Int>, Int>()
        while(scanner.hasNextLine()) {
            val lines = scanner.nextLine().split("->")
            val start = lines[0].trim().split(",")
            val stop = lines[1].trim().split(",")
            // println("start $start, stop $stop")
            val start_x = start[0].toInt()
            val start_y = start[1].toInt()
            val stop_x = stop[0].toInt()
            val stop_y = stop[1].toInt()

            if (start_x == stop_x || start_y == stop_y) {
                val x1 = min(start_x, stop_x)
                val x2 = max(start_x, stop_x)
                val y1 = min(start_y, stop_y)
                val y2 = max(start_y, stop_y)
                for (x in x1..x2) {
                    for(y in y1..y2) {
                        val p = points[Pair(x, y)]
                        if (p == null) {
                            points[Pair(x, y)] = 1
                        } else {
                            points[Pair(x, y)] = p + 1
                        }
                    }
                }
            }
        }

        val overlaps = points.values.filter { it > 1 }.count()
        println("answer $overlaps")
    }

    private fun check(value: Int, step: Int, stop: Int): Boolean {
        // println("check $value with $step until $stop")
        if (step > 0) return value <= stop
        return value >= stop
    }

    private fun star2(scanner: Scanner) {
        val points = mutableMapOf<Pair<Int, Int>, Int>()
        while(scanner.hasNextLine()) {
            val lines = scanner.nextLine().split("->")
            val start = lines[0].trim().split(",")
            val stop = lines[1].trim().split(",")
            // println("start $start, stop $stop")
            val start_x = start[0].toInt()
            val start_y = start[1].toInt()
            val stop_x = stop[0].toInt()
            val stop_y = stop[1].toInt()

            val step_x = if (start_x == stop_x) 0 else if (start_x > stop_x) -1 else 1
            val step_y = if (start_y == stop_y) 0 else if (start_y > stop_y) -1 else 1

            var x = start_x
            var y = start_y

            while (check(x, step_x, stop_x) && check(y, step_y, stop_y)) {
                // println("x $x, y $y")
                val p = points[Pair(x, y)]
                if (p == null) {
                    points[Pair(x, y)] = 1
                } else {
                    points[Pair(x, y)] = p + 1
                }
                x += step_x
                y += step_y
            }
        }
        val overlaps = points.values.filter { it > 1 }.count()
        println("answer $overlaps")
    }
}