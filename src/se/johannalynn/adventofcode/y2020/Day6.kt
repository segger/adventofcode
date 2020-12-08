package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day6 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(6, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var sum = 0
        scanner.useDelimiter("\\n\\n")
        while(scanner.hasNext()) {
            val input = scanner.next()
            val answers = input.split('\n')
            sum += calc(answers)
        }
        println(sum)
    }

    private fun calc(answers: List<String>): Int {
        val questions = mutableSetOf<Char>()
        answers.forEach {answer ->
            answer.forEach {
                questions.add(it)
            }
        }
        return questions.size
    }

    private fun calc2(answers: List<String>): Int {
        var questions = mutableSetOf<Char>()
        var ch = 'a'
        while (ch <= 'z') {
            questions.add(ch)
            ++ch
        }

        answers.forEach {answer ->
            questions = questions.intersect(answer.toSet()).toMutableSet()
        }
        return questions.size
    }

    private fun star2(scanner: Scanner) {
        var sum = 0
        scanner.useDelimiter("\\n\\n")
        while(scanner.hasNext()) {
            val input = scanner.next()
            val answers = input.split('\n')
            val size = calc2(answers)
            sum += size
        }
        println(sum)
    }

}