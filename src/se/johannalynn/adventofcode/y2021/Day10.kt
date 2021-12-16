package se.johannalynn.adventofcode.y2021

import se.johannalynn.adventofcode.y2021.Day.start
import java.lang.Integer.*
import java.lang.Math.abs
import java.util.*

object Day10 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(10, false)

        // star1(scanner)
        star2(scanner)
        // 219761541 (too low)
    }

    private fun star1(scanner: Scanner) {
        var sum = 0
        while(scanner.hasNextLine()) {
            val open: MutableList<String> = mutableListOf()
            val input = scanner.nextLine().chunked(1)
            for (it in input) {
                when(it) {
                    "(","[","{","<" -> open.add(it)
                    ")" -> {
                        if (open.removeAt(open.size-1) != "(") {
                            sum += 3
                            break
                        }
                    }
                    "]" -> {
                        if (open.removeAt(open.size-1) != "[") {
                            sum += 57
                            break
                        }
                    }
                    "}" -> {
                        if (open.removeAt(open.size-1) != "{") {
                            sum += 1197
                            break
                        }
                    }
                    ">" -> {
                        if (open.removeAt(open.size-1) != "<") {
                            sum += 25137
                            break
                        }
                    }
                }
            }
        }
        println("sum $sum")
    }

    private fun star2(scanner: Scanner) {
        val completions = mutableListOf<Long>()

        while(scanner.hasNextLine()) {
            val open: MutableList<String> = mutableListOf()
            val input = scanner.nextLine().chunked(1)
            for (it in input) {
                when(it) {
                    "(","[","{","<" -> open.add(it)
                    ")" -> {
                        if (open.removeAt(open.size-1) != "(") {
                            open.clear()
                            break
                        }
                    }
                    "]" -> {
                        if (open.removeAt(open.size-1) != "[") {
                            open.clear()
                            break
                        }
                    }
                    "}" -> {
                        if (open.removeAt(open.size-1) != "{") {
                            open.clear()
                            break
                        }
                    }
                    ">" -> {
                        if (open.removeAt(open.size-1) != "<") {
                            open.clear()
                            break
                        }
                    }
                }
            }

            if (open.size > 0) {
                var score = 0L

                for (idx in open.indices.reversed()) {
                    score *= 5L
                    val it = open[idx]
                    when(it) {
                        "(" -> score += 1L
                        "[" -> score += 2L
                        "{" -> score += 3L
                        "<" -> score +=4L
                    }
                }
                // println(score)
                completions.add(score)
            }
        }

        val middle = completions.sorted()[completions.size/2]
        println("middle $middle")
    }
}