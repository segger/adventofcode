package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day11 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(11, false)

        //star1(scanner)
        star2(scanner)
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

        val occupied = count(oldLayout)
        println(occupied)
    }

    private fun count(layout: List<String>): Int {
        return layout.map {row ->
            row.map {
                if (it == '#') 1 else 0
            }.sum()
        }.sum()
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

    private fun occupied2(layout: List<String>, colIdx: Int, rowIdx: Int): Int {
        val maxCol = layout.size
        val maxRow = layout[0].length

        var occupied = 0

        for (c in colIdx-1 .. colIdx+1) {
            for (r in rowIdx-1 .. rowIdx+1) {

                if (!(c == colIdx && r == rowIdx)) {
                    var tmp = Pair(r, c)
                    var foundSeat = false
                    var outOfBound = false
                    while(!foundSeat && !outOfBound) {
                        val tmpR = tmp.first
                        val tmpC = tmp.second
                        // println("($tmpR, $tmpC)")
                        if (!isOutOfBound(tmpR, tmpC, maxRow, maxCol)) {
                            if (!(tmpC == colIdx && tmpR == rowIdx)) {
                                val position = layout[tmpC][tmpR]
                                if (position == '#') {
                                    foundSeat = true
                                    occupied++
                                } else if (position == 'L') {
                                    foundSeat = true
                                }
                            }
                            tmp = move(tmpR, tmpC, r, c, rowIdx, colIdx)
                        } else {
                            outOfBound = true
                        }
                    }
                }
            }
        }
        return occupied
    }

    private fun move(inR: Int, inC: Int, r: Int, c: Int, rowIdx: Int, colIdx: Int): Pair<Int, Int> {
        var tmpR = inR
        var tmpC = inC
        if (r < rowIdx) tmpR--
        if (r > rowIdx) tmpR++
        if (c < colIdx) tmpC--
        if (c > colIdx) tmpC++
        val tmp = Pair(tmpR, tmpC)
        // println("move $inR, $inC to $tmpR, $tmpC")
        return tmp
    }

    private fun isOutOfBound(r: Int, c: Int, maxRow: Int, maxCol: Int): Boolean {
        val outOfBound = !((r in 0 until maxRow) && (c in 0 until maxCol))
        // println("isOutOfBound $r, $c -> $outOfBound")
        return outOfBound
    }

    private fun star2(scanner: Scanner) {
        val seatLayout = mutableListOf<String>()
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine()
            seatLayout.add(input)
        }
        var stabilized = false
        val newLayout = mutableListOf<String>()
        var oldLayout = seatLayout

        while(!stabilized) {
            // println(counter)
            oldLayout.forEachIndexed { colIdx, row ->
                var newRow = ""
                row.forEachIndexed { rowIdx, item ->
                    // print("$colIdx, $rowIdx: $item ")
                    val adjacent = occupied2(oldLayout, colIdx, rowIdx)
                    // print("$item - $adjacent ")
                    // print("$item")
                    if (item == 'L' && adjacent == 0) {
                        newRow += '#'
                    } else if (item == '#' && adjacent >= 5) {
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

        val occupied = count(oldLayout)
        println(occupied)
    }
}