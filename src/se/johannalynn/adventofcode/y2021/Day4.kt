package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.util.*
import kotlin.math.pow

object Day4 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(4, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val boards = solve(scanner)
        val winner = boards.minBy { it.first }!!
        println("winner final score ${winner.second}")
    }

    private fun star2(scanner: Scanner) {
        val boards = solve(scanner)
        val winner = boards.maxBy { it.first }!!
        println("loser final score ${winner.second}")
    }

    private fun solve(scanner: Scanner): List<Pair<Int, Int>> {
        val drawNbrs = scanner.nextLine().split(",").map { it.toInt() }

        val boards = mutableListOf<Pair<Int, Int>>()

        while(scanner.hasNextLine()) {
            scanner.nextLine()
            val board = HashMap<Int, Pair<Int, Int>>()

            for (row in 0..4) {
                scanner.nextLine().trim().split("\\s+".toRegex()).forEachIndexed { index, s ->
                    board[s.toInt()] = Pair(row, index)
                }
            }

            val rowBingo = mutableListOf(0,0,0,0, 0)
            val colBingo = mutableListOf(0,0,0,0, 0)

            val drawnNbrs = mutableListOf<Int>()
            // drawNbrs.forEachIndexed drawNbr@ { index, drawNbr ->
            for (index in drawNbrs.indices) {
                val drawNbr = drawNbrs[index]
                drawnNbrs.add(drawNbr)
                val mark = board[drawNbr]

                // mark.apply {  }
                val rows = if (mark != null) rowBingo[mark.first]++ else rowBingo
                val cols = if (mark != null) colBingo[mark.second]++ else colBingo

                if (rows == 4 || cols == 4) {
                    val unmarkedNbrs = board.keys.filter { !drawnNbrs.contains(it) }.sum()
                    val finalScore = unmarkedNbrs * drawNbr

                    boards.add(Pair(index, finalScore))
                    break
                }
            }
        }
        return boards
    }
}