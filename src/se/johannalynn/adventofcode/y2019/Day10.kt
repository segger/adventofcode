package se.johannalynn.adventofcode.y2019

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

object Day10 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(10, false)

        star1(scanner)
    }

    fun star1(scanner: Scanner) {

        var row = 0
        val map = HashSet<String>()
        while(scanner.hasNextLine()) {
            // samla in astroiderna
            val line = scanner.nextLine().mapIndexed { index, c -> "${c}${row},${index}" }.filter { it.startsWith("#") }.map { it.substring(1) }
            line.forEach { map.add(it) }
            row++
        }

        var max = Integer.MIN_VALUE
        var astroid = ""
        map.forEach {start ->
            val unique = HashSet<String>()
            //println("CHOOSE ${start}")
            map.forEach {
                if (!start.equals(it)) {
                    val start2 = start.split(",")
                    val other = it.split(",")
                    val angle = Math.atan2(start2[1].toDouble() - other[1].toDouble(), start2[0].toDouble() - other[0].toDouble())
                    //println("${start} with ${other} -> ${angle}")
                    unique.add(angle.toString())
                }
            }
            if (unique.size > max) {
                max = unique.size
                astroid = start
            }
        }
        println(max)
        println(astroid)
    }
}