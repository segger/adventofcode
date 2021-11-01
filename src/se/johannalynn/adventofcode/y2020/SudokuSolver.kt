package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*
import java.util.stream.IntStream
import kotlin.math.exp

object SudokuSolver {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(42, false)

        val board = readBoard(scanner)
        //backtracking(board)
        present(board)
    }

    fun readBoard(scanner: Scanner): List<IntArray> {
        val board = mutableListOf<IntArray>()
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine()
            val row = IntArray(9) {
                if (input[it] == '.')  {
                    0
                } else {
                    Character.getNumericValue(input[it])
                }
            }
            board.add(row)
        }
        return board
    }

    fun present(board: List<IntArray>) {
        board.forEach { row ->
            row.forEach { value ->
                print("$value ")
            }
            println()
        }
    }

    fun backtracking(board: List<IntArray>): Boolean {
        board.forEachIndexed { row, cols ->
            cols.forEachIndexed { col, value ->
                if (board[row][col] == 0) {
                    for (k in 1..9) {
                        board[row][col] = k
                        if (isValid(board, row, col) && backtracking(board)) {
                            return true
                        }
                        board[row][col] = 0
                    }
                    return false
                }
            }
        }
        return true
    }

    private fun isValid(board: List<IntArray>, row: Int, col: Int): Boolean {
        val r = rowConstraint(board, row)
        val c = colConstraint(board, col)
        val s = squareConstraint(board, row, col)
        return r && c && s
    }

    private fun rowConstraint(board: List<IntArray>, row: Int): Boolean {
        val constraints = BooleanArray(9)
        return IntStream.range(0, 9).allMatch { checkConstraint(board, row, constraints, it) }
    }

    private fun colConstraint(board: List<IntArray>, col: Int): Boolean {
        val constraints = BooleanArray(9)
        return IntStream.range(0, 9).allMatch { checkConstraint(board, it, constraints, col) }
    }

    private fun squareConstraint(board: List<IntArray>, row: Int, col: Int): Boolean {
        val constraints = BooleanArray(9)
        val squareRow = ((row/3)*3)
        val squareCol = ((col/3)*3)
        for (r in squareRow..(squareRow+2)) {
            for (c in squareCol..(squareCol+2)) {
                if (!checkConstraint(board, r, constraints, c)) return false
            }
        }
        return true
    }

    private fun checkConstraint(board: List<IntArray>, row: Int, constraints: BooleanArray, col: Int): Boolean {
        val checkVal = board[row][col]
        if (checkVal != 0) {
            if (!constraints[checkVal - 1]) {
                constraints[checkVal - 1] = true
            } else {
                return false
            }
        }
        return true
    }
}