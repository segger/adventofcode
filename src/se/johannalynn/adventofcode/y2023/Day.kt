package se.johannalynn.adventofcode.y2023

import java.io.File
import java.util.*

object Day {
    fun start(day: Int, usePre: Boolean) : Scanner {
        println("Day" + day)

        var pre = ""
        if (usePre)
            pre = "_pre"

        val inFileName = "input/2023/day" + day + pre + ".txt"
        return Scanner(File(inFileName))
    }

    fun file(day: Int, usePre: Boolean): String {
        println("Day" + day)
        val fileName = if (usePre) "input/2023/day${day}_pre.txt" else "input/2023/day${day}.txt"
        return File(fileName).inputStream().bufferedReader().use { it.readText() }
    }
}