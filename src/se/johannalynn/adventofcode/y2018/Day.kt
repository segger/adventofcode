package se.johannalynn.adventofcode.y2018

import java.io.File
import java.util.*

object Day {

    fun start(day: Int, usePre: Boolean) : Scanner {
        println("Day" + day)

        var pre = ""
        if (usePre)
            pre = "_pre"

        val inFileName = "2018/input/day" + day + pre + ".txt"
        return Scanner(File(inFileName))
    }
}