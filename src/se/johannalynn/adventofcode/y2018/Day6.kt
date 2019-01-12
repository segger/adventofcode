package se.johannalynn.adventofcode.y2018

import java.util.*
import kotlin.collections.HashMap

object Day6 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(6, false)

        val distMax = 10000
        //star1(scanner)
        star2(scanner, distMax)
    }

    private fun star1(scanner: Scanner) {
        var minX = Int.MAX_VALUE;
        var minY = Int.MAX_VALUE;
        var maxX = Int.MIN_VALUE;
        var maxY = Int.MIN_VALUE;

        var letter = 1
        val definite = HashMap<Int, Int>()
        val points = ArrayList<IntArray>()
        while(scanner.hasNextLine()) {
            val vector = scanner.nextLine().split(", ")
            val x = vector[0].toInt()
            val y = vector[1].toInt()
            points.add(intArrayOf(x, y))

            if(x < minX) {
                minX = x
            }
            if(x > maxX) {
                maxX = x
            }
            if(y < minY) {
                minY = y
            }
            if(y > maxY) {
                maxY = y
            }
            definite.put(letter++, 0)
        }
        var manhattan = Array(maxY + 1){ IntArray(maxX + 1) }
        for((i, arr) in manhattan.withIndex()) {
            for((j, ch) in arr.withIndex()) {
                var letter = 1
                var minDistance = Int.MAX_VALUE
                var minLetter = Int.MIN_VALUE
                for(point in points) {
                    var distance = distanceTo(i, j, point[1], point[0])
                    if(distance == minDistance) {
                        minLetter = 0
                    }
                    if(distance < minDistance) {
                        minDistance = distance
                        minLetter = letter
                    }
                    letter++
                }
                manhattan[i][j] = minLetter
                if(i == 0 || i == maxY || j == 0 || j == maxX) {
                    definite.remove(minLetter)
                }
            }
        }

        for(arr in manhattan) {
            for(letter in arr) {
                if(definite.keys.contains(letter)) {
                    var count = definite.get(letter)!!
                    definite.put(letter, ++count)
                }
            }
        }
        val max = definite.maxBy { it.value }
        println(max!!.value)
    }

    private fun star2(scanner: Scanner, distMax: Int) {

        var minX = Int.MAX_VALUE;
        var minY = Int.MAX_VALUE;
        var maxX = Int.MIN_VALUE;
        var maxY = Int.MIN_VALUE;

        val points = ArrayList<IntArray>()
        while(scanner.hasNextLine()) {
            val vector = scanner.nextLine().split(", ")
            val x = vector[0].toInt()
            val y = vector[1].toInt()
            points.add(intArrayOf(x, y))

            if(x < minX) {
                minX = x
            }
            if(x > maxX) {
                maxX = x
            }
            if(y < minY) {
                minY = y
            }
            if(y > maxY) {
                maxY = y
            }
        }

        var region = 0
        var manhattan = Array(maxY + 1){ IntArray(maxX + 1) }
        for((i, arr) in manhattan.withIndex()) {
            for((j, ch) in arr.withIndex()) {
                var totDistance = 0
                for(point in points) {
                    var distance = distanceTo(i, j, point[1], point[0])
                    totDistance += distance
                }
                manhattan[i][j] = totDistance
                if (totDistance < distMax) {
                    region++
                }
            }
        }

        println(region)
    }

    private fun distanceTo(iy: Int, ix: Int, y: Int, x: Int): Int {
        return Math.abs(ix-x) + Math.abs(iy-y)
    }
}