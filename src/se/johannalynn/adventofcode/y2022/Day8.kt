package se.johannalynn.adventofcode.y2022

import se.johannalynn.adventofcode.y2022.Day.start
import java.util.*

object Day8 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(8, true)

        // star1b(scanner)
        // 1451 too low
        star2(scanner)
    }

    private fun calc(value: Int, list: List<Int>, last: Boolean): Int {
        val trees = if (last) list.takeLastWhile { it < value }.count() else list.takeWhile { it < value }.count()
        return if (trees < list.size) {
            trees + 1
        } else trees
    }

    private fun star2(scanner: Scanner) {
        val trees = mutableListOf<List<Int>>()
        var length = 0
        while (scanner.hasNextLine()) {
            val row = scanner.nextLine().chunked(1).map { it.toInt() }.toList()
            length = row.size
            trees.add(row)
        }
        var max = 0
        trees.forEachIndexed { col, rows ->
            rows.forEachIndexed { row, value ->

                // println("[$col,$row]: $value")

                val left = calc(value, rows.subList(0, row), true)

                val right = if (row+1 < length) {
                    calc(value, rows.subList(row+1, length), false)
                } else 0

                val columns = trees.map { it[row] }
                val down = if (col+1 < length) {
                    calc(value, columns.subList(col+1, length), false)
                } else 0

                val up = calc(value, columns.subList(0, col), true)

                // println("left: $left, right: $right, up: $up, down: $down")
                val scenicScore = left * right * up * down
                // println("scenicScore $scenicScore")
                if (scenicScore > max) {
                    max = scenicScore
                }
            }
        }
        println("max: $max")
    }

    private fun star1b(scanner: Scanner) {
        val trees = mutableListOf<List<Int>>()
        var length = 0
        while (scanner.hasNextLine()) {
            val row = scanner.nextLine().chunked(1).map { it.toInt() }.toList()
            length = row.size
            trees.add(row)
        }
        var visible = 0
        trees.forEachIndexed { col, rows ->
            rows.forEachIndexed { row, value ->
                if (col == 0 || col == length - 1 || row == 0 || row == length - 1) {
                    visible++
                } else {
                    val left = rows.withIndex().find { it.value >= value }?.index ?: -1
                    val right = rows.withIndex().findLast { it.value >= value }?.index ?: length
                    val up = trees.map { it[row] }.withIndex().find { it.value >= value }?.index ?: -1
                    val down = trees.map { it[row] }.withIndex().findLast { it.value >= value }?.index ?: length

                    if (left >= row || right <= row || up >= col || down <= col) {
                        visible++
                    }
                }
            }
        }
        println("visible $visible")
    }

    private fun star1(scanner: Scanner) {
        val trees = mutableListOf<Int>()
        var length = 0
        // var height = 0
        while(scanner.hasNextLine()) {
            val row = scanner.nextLine().chunked(1).map { it.toInt() }.toList()
            length = row.size
            trees.addAll(row)
            // height++
        }
        var visible = 0
        // println("length $length")
        // println("height $height")

        trees.forEachIndexed { idx, it ->
            // println("$idx: $it")
            var seen = false

            if ((idx % length) - 1 < 0 || (idx % length) + 1 >= length || (idx - length) < 0 || ((length*length) - idx) < length) {
                seen = true
            }

            if (!seen && left(idx, it, length, trees)) {
                // println("left")
                seen = true
            }
            if (!seen && right(idx, it, length, trees)) {
                // println("right")
                seen = true
            }
            if (!seen && up(idx, it, length, trees)) {
                // println("up")
                seen = true
            }
            if(!seen && down(idx, it, length, trees)) {
                // println("down")
                seen = true
            }

            if (seen) {
                visible++
            } else {
                // println("idx $idx not visible")
            }
        }

        println("visible $visible")
    }

    private fun left(idx: Int, value: Int, length: Int, trees: MutableList<Int>): Boolean {
        return if((idx % length) - 1 < 0) {
            value > trees[idx]
        } else {
            if (value <= trees[idx-1]) {
                false
            } else {
                left(idx - 1, value, length, trees)
            }
        }
    }

    private fun right(idx: Int, value: Int, length: Int, trees: MutableList<Int>): Boolean {
        return if ((idx % length) + 1 >= length) {
            value > trees[idx]
        } else {
            if (value <= trees[idx+1]) {
                false
            } else {
                right(idx + 1, value, length, trees)
            }
        }
    }

    private fun up(idx: Int, value: Int, length: Int, trees: MutableList<Int>): Boolean {
        return if ((idx - length) < 0) {
            value > trees[idx]
        } else {
            if (value <= trees[idx - length]) {
                false
            } else {
                up(idx - length, value, length, trees)
            }
        }
    }

    private fun down(idx: Int, value: Int, length: Int, trees: MutableList<Int>): Boolean {
        val next = ((length*length) - idx)
        return if (next < length) {
            value > trees[idx]
        } else {
            if (value <= trees[next]) {
                false
            } else {
                down(next, value, length, trees)
            }
        }
    }
}