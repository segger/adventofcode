package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.Integer.*
import java.lang.Math.abs
import java.util.*

object Day13 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(13, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val input = mutableListOf<Pair<Int, Int>>()
        val folds = mutableListOf<Pair<String, Int>>()

        var maxX = 0
        var maxY = 0
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine()
            if (line.isNotEmpty()) {
                if (line.startsWith("fold")) {
                    val folding = line.split("=")
                    val firstPart = folding[0]
                    val order = firstPart.substring(firstPart.length-2).trim()
                    val nbr = folding[1].trim().toInt()
                    // println("order $order, nbr $nbr")
                    folds.add(Pair(order, nbr))
                } else {
                    val points = line.split(",")
                    val x = points[0].toInt()
                    val y = points[1].toInt()
                    // println("x $x, y $y")
                    input.add(Pair(x, y))
                    if (x > maxX) {
                        maxX = x
                    }
                    if (y > maxY) {
                        maxY = y
                    }
                }
            }
        }

        var matrix = Array(maxY+1) { IntArray(maxX+1) }
        input.forEach {
            matrix[it.second][it.first] = 1
        }

        val fold = folds[0]
        var x = maxX
        var y = maxY
        // println("${fold.first}, ${fold.second}")
        if (fold.first == "y") {
            y = fold.second

            val matrix2 = Array(y) { IntArray(x + 1) }
            for (r in 0..y-1) {
                matrix[r].forEachIndexed { index, i ->
                    matrix2[r][index] = i
                }
            }

            var rowIdx = 0
            // println("maxY $maxY, y $y")
            for (r in maxY downTo y) {
                matrix[r].forEachIndexed { index, i ->
                    if (i == 1) {
                        matrix2[rowIdx][index] = i
                    }
                }
                rowIdx++
            }

            matrix = matrix2
        } else {
            x = fold.second

            val matrix2 = Array(y + 1) { IntArray(x) }
            for (r in 0..y) {
                for (c in 0..x-1) {
                    matrix2[r][c] = matrix[r][c]
                }
                var col = 0
                for (c in maxX downTo x) {
                    if (matrix[r][c] == 1) {
                        matrix2[r][col] = 1
                    }
                    col++
                }
            }

            matrix = matrix2
        }

        var sum = 0
        matrix.forEach { row ->
            row.forEach { col ->
                if (col == 1) {
                    sum++
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }
        println("sum $sum")
    }

    private fun star2(scanner: Scanner) {
        val input = mutableListOf<Pair<Int, Int>>()
        val folds = mutableListOf<Pair<String, Int>>()

        var maxX = 0
        var maxY = 0
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine()
            if (line.isNotEmpty()) {
                if (line.startsWith("fold")) {
                    val folding = line.split("=")
                    val firstPart = folding[0]
                    val order = firstPart.substring(firstPart.length-2).trim()
                    val nbr = folding[1].trim().toInt()
                    // println("order $order, nbr $nbr")
                    folds.add(Pair(order, nbr))
                } else {
                    val points = line.split(",")
                    val x = points[0].toInt()
                    val y = points[1].toInt()
                    // println("x $x, y $y")
                    input.add(Pair(x, y))
                    if (x > maxX) {
                        maxX = x
                    }
                    if (y > maxY) {
                        maxY = y
                    }
                }
            }
        }

        maxX++
        maxY++
        var matrix = Array(maxY) { IntArray(maxX) }
        input.forEach {
            matrix[it.second][it.first] = 1
        }
        // println("${matrix.size} - ${matrix[0].size}")

        // matrix[7][maxX] = 1

        /*
        matrix.forEach { row ->
            row.forEach { col ->
                if (col == 1) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        } */

        for (fold in folds) {
            // val fold = folds[0]
            var x = maxX
            var y = maxY
            // println("${fold.first}, ${fold.second}")
            if (fold.first == "y") {
                y = fold.second

                val matrix2 = Array(y) { IntArray(x) }
                for (r in 0..y-1) {
                    matrix[r].forEachIndexed { index, i ->
                        matrix2[r][index] = i
                    }
                }

                var rowIdx = 0
                // println("maxY $maxY, y $y")
                for (r in maxY-1 downTo y) {
                    // println("r $r maxY $maxY y $y")
                    matrix[r].forEachIndexed { index, i ->
                        if (i == 1) {
                            matrix2[rowIdx][index] = i
                        }
                    }
                    rowIdx++
                }

                matrix = matrix2
            } else {
                x = fold.second

                val matrix2 = Array(y) { IntArray(x) }
                for (r in 0..y-1) {
                    for (c in 0..x-1) {
                        matrix2[r][c] = matrix[r][c]
                    }
                    var col = 0
                    for (c in maxX-1 downTo x) {
                        // println("c $c maxX $maxX x $x")
                        if (matrix[r][c] == 1) {
                            matrix2[r][col] = 1
                        }
                        col++
                    }
                }

                matrix = matrix2
            }

            maxX = x
            maxY = y
        }

        // println("matrix ${matrix.size} ${matrix[0].size}")
        matrix.forEach { row ->
            row.forEach { col ->
                if (col == 1) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }
    }
}