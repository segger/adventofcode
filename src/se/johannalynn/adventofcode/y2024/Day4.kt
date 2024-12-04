package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*
import kotlin.math.abs

object Day4 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(4, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {

        var row = 0
        val matrix = mutableMapOf<Pair<Int, Int>, String>()
        val x = mutableListOf<Pair<Int, Int>>()
        while (scanner.hasNextLine()) {
            scanner.nextLine().split("").forEachIndexed { col, letter ->
                if (arrayOf("X","M","A","S").any { it == letter }) {
                    val position = Pair(row, col)
                    matrix[position] = letter
                    if (letter == "X") {
                        x.add(position)
                    }
                }
            }
            row++
        }

        var sum = 0
        x.forEach { (row, col) ->
            // println("x at $row $col")
            if (matrix[Pair(row+1, col)] == "M" && matrix[Pair(row+2, col)] == "A" && matrix[Pair(row+3, col)] == "S") {
                sum++
            }
            if (matrix[Pair(row+1, col+1)] == "M" && matrix[Pair(row+2, col+2)] == "A" && matrix[Pair(row+3, col+3)] == "S") {
                sum++
            }
            if (matrix[Pair(row+1, col-1)] == "M" && matrix[Pair(row+2, col-2)] == "A" && matrix[Pair(row+3, col-3)] == "S") {
                sum++
            }
            if (matrix[Pair(row-1, col)] == "M" && matrix[Pair(row-2, col)] == "A" && matrix[Pair(row-3, col)] == "S") {
                sum++
            }
            if (matrix[Pair(row-1, col+1)] == "M" && matrix[Pair(row-2, col+2)] == "A" && matrix[Pair(row-3, col+3)] == "S") {
                sum++
            }
            if (matrix[Pair(row-1, col-1)] == "M" && matrix[Pair(row-2, col-2)] == "A" && matrix[Pair(row-3, col-3)] == "S") {
                sum++
            }
            if (matrix[Pair(row, col+1)] == "M" && matrix[Pair(row, col+2)] == "A" && matrix[Pair(row, col+3)] == "S") {
                sum++
            }
            if (matrix[Pair(row, col-1)] == "M" && matrix[Pair(row, col-2)] == "A" && matrix[Pair(row, col-3)] == "S") {
                sum++
            }
        }
        println(sum)
    }

    private fun star2(scanner: Scanner) {

        var row = 0
        val matrix = mutableMapOf<Pair<Int, Int>, String>()
        val a = mutableListOf<Pair<Int, Int>>()
        while (scanner.hasNextLine()) {
            scanner.nextLine().split("").forEachIndexed { col, letter ->
                if (arrayOf("M","A","S").any { it == letter }) {
                    val position = Pair(row, col)
                    matrix[position] = letter
                    if (letter == "A") {
                        // println(position)
                        a.add(position)
                    }
                }
            }
            row++
        }

        var sum = 0
        a.forEach { (row, col) ->
            // println("a at $row $col")
            if (matrix[Pair(row+1, col+1)] == "M") {
                if (matrix[Pair(row+1, col-1)] == "M" && matrix[Pair(row-1, col+1)] == "S" && matrix[Pair(row-1, col-1)] == "S") {
                    sum++
                } else if (matrix[Pair(row-1, col+1)] == "M" && matrix[Pair(row-1, col-1)] == "S" && matrix[Pair(row+1, col-1)] == "S") {
                    sum++
                }
            } else if (matrix[Pair(row+1, col-1)] == "M") {
                if (matrix[Pair(row+1, col+1)] == "M" && matrix[Pair(row-1, col-1)] == "S" && matrix[Pair(row-1, col+1)] == "S") {
                    sum++ // will not happen
                } else if (matrix[Pair(row-1, col-1)] == "M" && matrix[Pair(row-1, col+1)] == "S" && matrix[Pair(row+1, col+1)] == "S") {
                    sum++
                }
            } else if (matrix[Pair(row-1, col+1)] == "M") {
                if (matrix[Pair(row-1, col-1)] == "M" && matrix[Pair(row+1, col+1)] == "S" && matrix[Pair(row+1, col-1)] == "S") {
                    sum++
                } else if (matrix[Pair(row+1, col+1)] == "M" && matrix[Pair(row+1, col-1)] == "S" && matrix[Pair(row-1, col-1)] == "S") {
                    sum++
                }
            } else if (matrix[Pair(row-1, col-1)] == "M") {
                if (matrix[Pair(row-1, col+1)] == "M" && matrix[Pair(row+1, col-1)] == "S" && matrix[Pair(row+1, col+1)] == "S") {
                    sum++
                } else if (matrix[Pair(row+1, col-1)] == "M" && matrix[Pair(row+1, col+1)] == "S" && matrix[Pair(row-1, col+1)] == "S") {
                    sum++
                }
            }
        }
        println(sum)
    }
}
