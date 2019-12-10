package se.johannalynn.adventofcode.y2019

import java.util.*

object Day8 {

    /**
     * 123456789012 (3x2)
     * 0222112222120000 (2x2)
     */

    @JvmStatic
    fun main(args: Array<String>) {
        val pre = false
        val scanner = Day.start(8, pre)

        var width = 25
        var height = 6
        if(pre) {
            width = 2
            height = 2
        }

        // star1(scanner, width, height)
        // Too low: 2352
        star2(scanner, width, height)
    }

    fun star2(scanner: Scanner, width: Int, height: Int) {
        while (scanner.hasNextLine()) {
            val image = scanner.nextLine().map { it.toString().toInt() }
            val count = width * height
            val layers = image.chunked(count)

            //layers.forEach { println(it) }

            val res = Array(height) { IntArray(width, { -1 }) }
            layers.forEach {
                for(i in 0..height-1) {
                    for(j in 0..width-1) {
                        //print(i*height + j)
                        val color = it[i*width + j]
                        //print(color)
                        if (res[i][j] == -1 && color != 2) {
                            res[i][j] = color
                        }
                    }
                }
            }

            res.forEach {
                it.forEach {
                    if(it == 1) {
                        print(" ${it} ")
                    } else {
                        print(" - ")
                    }
                }
                println()
            }
        }
    }

    fun star1(scanner: Scanner, width: Int, height: Int) {
        while (scanner.hasNextLine()) {
            val image = scanner.nextLine().map { it.toString().toInt() }
            val count = width * height
            val layers = image.chunked(count)

            //layers.forEach { println(it.count { it == 0 }) }

            val hashLayer = layers.sortedBy { it.count { it == 0 } }.first()
            // hashLayer.forEach { println(it.count { it == 0 }) }
            println(hashLayer.count { it == 0 })

            val ones = hashLayer.count { it == 1 }
            val twos = hashLayer.count { it == 2 }
            println(ones * twos)
        }
    }
}