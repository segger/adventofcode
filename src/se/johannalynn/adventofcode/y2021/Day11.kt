package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.Integer.*
import java.lang.Math.abs
import java.util.*

object Day11 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(11, false)

        // star1(scanner)
        star2(scanner)
    }

    class Octopus(val row: Int, val col: Int, var level: Int, var flashed: Boolean)

    private fun star1(scanner: Scanner) {
        var row = 0
        val octopuses = mutableMapOf<Pair<Int, Int>, Octopus>()
        while(scanner.hasNextLine()) {
            var col = 0
            scanner.nextLine().chunked(1).forEach {
                octopuses[Pair(row, col)] = Octopus(row, col, it.toInt(), false)
                col++
            }
            row++
        }


        // step
        var totalFlashes = 0
        for (i in 1..100) {
            // println("step $i")

            for (r in 0..9) {
                for (c in 0..9) {
                    octopuses[Pair(r, c)]!!.level++
                }
            }

            var flashes = 0
            var done = false
            var flashing = octopuses.values.filter { it.level > 9 }
            // println("flashing ${flashing.size}")
            while (!done) {
                for (flasho in flashing) {
                    val o = octopuses[Pair(flasho.row, flasho.col)]!!
                    o.flashed = true
                    o.level = 0
                    flashes++

                    val topleft = octopuses[Pair(flasho.row-1, flasho.col-1)]
                    if (topleft != null && !topleft.flashed) {
                        octopuses[Pair(flasho.row-1, flasho.col-1)]!!.level++
                    }
                    val top = octopuses[Pair(flasho.row-1, flasho.col)]
                    if (top != null && !top.flashed) {
                        octopuses[Pair(flasho.row-1, flasho.col)]!!.level++
                    }
                    val topright = octopuses[Pair(flasho.row-1, flasho.col+1)]
                    if (topright != null && !topright.flashed) {
                        octopuses[Pair(flasho.row-1, flasho.col+1)]!!.level++
                    }
                    val right = octopuses[Pair(flasho.row, flasho.col+1)]
                    if (right != null && !right.flashed) {
                        octopuses[Pair(flasho.row, flasho.col+1)]!!.level++
                    }
                    val bottomright = octopuses[Pair(flasho.row+1, flasho.col+1)]
                    if (bottomright != null && !bottomright.flashed) {
                        octopuses[Pair(flasho.row+1, flasho.col+1)]!!.level++
                    }
                    val bottom = octopuses[Pair(flasho.row+1, flasho.col)]
                    if (bottom != null && !bottom.flashed) {
                        octopuses[Pair(flasho.row+1, flasho.col)]!!.level++
                    }
                    val bottomleft = octopuses[Pair(flasho.row+1, flasho.col-1)]
                    if (bottomleft != null && !bottomleft.flashed) {
                        octopuses[Pair(flasho.row+1, flasho.col-1)]!!.level++
                    }
                    val left = octopuses[Pair(flasho.row, flasho.col-1)]
                    if (left != null && !left.flashed) {
                        octopuses[Pair(flasho.row, flasho.col-1)]!!.level++
                    }
                }

                flashing = octopuses.values.filter { it.level > 9 }
                // println("flashing ${flashing.size}")

                if (flashing.isEmpty()) {
                    done = true
                }
            }

            for (r in 0..9) {
                for (c in 0..9) {
                    // print("${octopuses[Pair(r, c)]!!.level} ")
                    octopuses[Pair(r, c)]!!.flashed = false
                }
                // println()
            }

            totalFlashes += flashes

            // println("flashes $flashes")
        }

        println("totalFlashes $totalFlashes")
    }

    private fun star2(scanner: Scanner) {
        var row = 0
        val octopuses = mutableMapOf<Pair<Int, Int>, Octopus>()
        while(scanner.hasNextLine()) {
            var col = 0
            scanner.nextLine().chunked(1).forEach {
                octopuses[Pair(row, col)] = Octopus(row, col, it.toInt(), false)
                col++
            }
            row++
        }


        // step
        for (i in 1..500) {
            // println("step $i")

            for (r in 0..9) {
                for (c in 0..9) {
                    octopuses[Pair(r, c)]!!.level++
                }
            }

            var flashes = 0
            var done = false
            var flashing = octopuses.values.filter { it.level > 9 }
            // println("flashing ${flashing.size}")
            while (!done) {
                for (flasho in flashing) {
                    val o = octopuses[Pair(flasho.row, flasho.col)]!!
                    o.flashed = true
                    o.level = 0
                    flashes++

                    val topleft = octopuses[Pair(flasho.row-1, flasho.col-1)]
                    if (topleft != null && !topleft.flashed) {
                        octopuses[Pair(flasho.row-1, flasho.col-1)]!!.level++
                    }
                    val top = octopuses[Pair(flasho.row-1, flasho.col)]
                    if (top != null && !top.flashed) {
                        octopuses[Pair(flasho.row-1, flasho.col)]!!.level++
                    }
                    val topright = octopuses[Pair(flasho.row-1, flasho.col+1)]
                    if (topright != null && !topright.flashed) {
                        octopuses[Pair(flasho.row-1, flasho.col+1)]!!.level++
                    }
                    val right = octopuses[Pair(flasho.row, flasho.col+1)]
                    if (right != null && !right.flashed) {
                        octopuses[Pair(flasho.row, flasho.col+1)]!!.level++
                    }
                    val bottomright = octopuses[Pair(flasho.row+1, flasho.col+1)]
                    if (bottomright != null && !bottomright.flashed) {
                        octopuses[Pair(flasho.row+1, flasho.col+1)]!!.level++
                    }
                    val bottom = octopuses[Pair(flasho.row+1, flasho.col)]
                    if (bottom != null && !bottom.flashed) {
                        octopuses[Pair(flasho.row+1, flasho.col)]!!.level++
                    }
                    val bottomleft = octopuses[Pair(flasho.row+1, flasho.col-1)]
                    if (bottomleft != null && !bottomleft.flashed) {
                        octopuses[Pair(flasho.row+1, flasho.col-1)]!!.level++
                    }
                    val left = octopuses[Pair(flasho.row, flasho.col-1)]
                    if (left != null && !left.flashed) {
                        octopuses[Pair(flasho.row, flasho.col-1)]!!.level++
                    }
                }

                flashing = octopuses.values.filter { it.level > 9 }
                // println("flashing ${flashing.size}")

                if (flashing.isEmpty()) {
                    done = true
                }
            }

            for (r in 0..9) {
                for (c in 0..9) {
                    // print("${octopuses[Pair(r, c)]!!.level} ")
                    octopuses[Pair(r, c)]!!.flashed = false
                }
                // println()
            }

            // println("flashes $flashes")

            if (flashes == 100) {
                println("yey $i")
                break
            }
        }
    }
}