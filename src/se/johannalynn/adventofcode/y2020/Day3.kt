package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*
import kotlin.math.exp

object Day3 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(3, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var count = 0
        var right = 3
        var down = 0
        while (scanner.hasNextLine()) {
            if (down == 0) scanner.nextLine()
            val row = scanner.nextLine()
            // println(row[right % row.length])
            if (row[right % row.length] == '#') {
                count++
            }
            down++
            right += 3
        }
        println(count)
    }

    private fun star2(scanner: Scanner) {
        val count = intArrayOf(0, 0, 0, 0)
        val rightShift = intArrayOf(1, 3, 5, 7)
        var right = listOf(1, 3, 5, 7)
        var first = true
        var rightDownTwo = 1
        var downTwo = false
        var countDownTwo = 0
        while (scanner.hasNextLine()) {
            if (first) {
                scanner.nextLine()
                first = false

            }
            val row = scanner.nextLine()
            // println("row $row")
            right.forEachIndexed { index, it ->
                // val findIdx = it % row.length
                // val spot = row[it % row.length]
                // println("$it : $findIdx -> $spot")
                if (row[it % row.length] == '#') {
                    count[index]++
                }
            }
            if (downTwo) {
                val tmp = row[rightDownTwo % row.length]
                // println("$row -> $rightDownTwo -> $tmp")
                if(row[rightDownTwo % row.length] == '#') {
                    countDownTwo++
                }
            }

            right = right.mapIndexed { index, it ->
                it + rightShift[index]
            }
            if (downTwo) {
                rightDownTwo++
            }
            downTwo = !downTwo
        }
        // println(countDownTwo)
        val product = count.reduce{ product, factor -> product * factor }
        println(product * countDownTwo)
    }

}