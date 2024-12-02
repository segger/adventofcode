package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*
import kotlin.math.abs

object Day2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(2, true)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {

        var nbrOfSafe = 0
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            val reports = next.split(" ")
            println(reports)

            val safe = checkReport(reports)

            if (safe) {
                println("safe $reports")
                nbrOfSafe++
            }
        }

        println(nbrOfSafe)
    }

    private fun star2(scanner: Scanner) {

        var nbrOfSafe = 0
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            val reports = next.split(" ")
            // println(reports)

            var safe = checkReport(reports)
            for (i in reports.indices) {
                if (!safe) {
                    val report = reports.toMutableList()
                    report.removeAt(i)
                    safe = checkReport(report)
                }
            }

            if (safe) {
                nbrOfSafe++
            }
        }

        println(nbrOfSafe)
    }

    private fun checkReport(report: List<String>): Boolean {
        var safe = true
        var idx = 0
        var curr = report[idx].toInt()
        var nbr = report[++idx].toInt()
        val incr = curr < nbr
        if (curr != nbr) {
            do {
                safe = abs(curr - nbr) in 1..3
                if (incr && curr > nbr || !incr && curr < nbr) {
                    safe = false
                }

                curr = nbr
                nbr = report[++idx].toInt()
            } while (safe && idx < report.size - 1)
            if (safe) {
                safe = abs(curr - nbr) in 1..3
                if (incr && curr > nbr || !incr && curr < nbr) {
                    safe = false
                }
            }
        } else {
            safe = false
        }
        return safe
    }
}
