package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day11 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(11, false)

        star1(scanner)
        // star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val seatLayout = mutableListOf<String>()
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine()
            seatLayout.add(input)
        }
        var stabilized = false
        val newLayout = mutableListOf<String>()
        var oldLayout = seatLayout

        while(!stabilized) {
            oldLayout.forEachIndexed { colIdx, row ->
                var newRow = ""
                row.forEachIndexed { rowIdx, item ->
                    // print("$colIdx, $rowIdx: $item ")
                    val adjacent = occupied(oldLayout, colIdx, rowIdx)
                    // print("$item - $adjacent ")
                    // print("$item")
                    if (item == 'L' && adjacent == 0) {
                        newRow += '#'
                    } else if (item == '#' && adjacent >= 4) {
                        newRow += 'L'
                    } else {
                        newRow += item
                    }
                }
                newLayout.add(newRow)
                // println()
            }
            if (compareLayout(oldLayout, newLayout)) {
                stabilized = true
                // println("done")
            }
            oldLayout = newLayout.toMutableList()
            newLayout.clear()
        }

        val occupied = oldLayout.map {row ->
            row.map {
                if (it == '#') 1 else 0
            }.sum()
        }.sum()

        println(occupied)
    }

    private fun compareLayout(oldLayout: List<String>, newLayout: List<String>): Boolean {
        oldLayout.forEachIndexed{ idx, row ->
            if (row != newLayout[idx]) {
                return false
            }
        }
        return true
    }

    private fun occupied(layout: List<String>, colIdx: Int, rowIdx: Int): Int {
        val maxCol = layout.size
        val maxRow = layout[0].length

        var occupied = 0
        for (c in colIdx-1 .. colIdx+1) {
            if (c in 0 until maxCol) {
                for (r in rowIdx-1 .. rowIdx+1) {
                    if (r in 0 until maxRow) {
                        if (!(c == colIdx && r == rowIdx)) {
                            if (layout[c][r] == '#') {
                                occupied++
                            }
                        }
                    }
                }
            }
        }
        return occupied
    }

    private fun star2(scanner: Scanner) {

    }
}