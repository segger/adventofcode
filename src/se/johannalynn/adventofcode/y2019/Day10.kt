package se.johannalynn.adventofcode.y2019

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

object Day10 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(10, true)

        //star1(scanner)
        val x = 29
        val y = 28
        star2(scanner, 8, 3)
    }

    fun star2(scanner: Scanner, x: Int, y: Int) {

        var row = 0
        val set = HashSet<String>()
        while(scanner.hasNextLine()) {
            // samla in astroiderna
            val line = scanner.nextLine().mapIndexed { index, c -> "${c}${index},${row}" }.filter { it.startsWith("#") }.map { it.substring(1) }
            line.forEach { set.add(it) }
            row++
        }

        val map = HashMap<Double, ArrayList<Astroid>>()

        set.forEach {
            if (!"${x},${y}".equals(it)) {
                val station = "${x},${y}".split(",").map { it.toDouble() }
                val astroid = it.split(",").map { it.toDouble() }
                val angle = Math.atan2(astroid[1] - station[1], astroid[0] - station[0])
                val degrees = Math.toDegrees(angle)
                val orderDegrees = (((degrees - 270) % 360) + 360) % 360
                val distance = Math.abs(astroid[1] - station[1]) + Math.abs(astroid[0] - station[0])
                println("${station} to ${astroid} -> ${orderDegrees} at ${distance}")

                var astroids = map.get(orderDegrees)
                if (astroids == null) {
                    astroids = ArrayList()
                }
                astroids.add(Astroid(it, distance))
                map.put(orderDegrees, astroids)
            }
        }

        map.forEach {
            // it.value.sortBy {  }
        }
    }

    class Astroid(val xy: String, val distance: Double) {}

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