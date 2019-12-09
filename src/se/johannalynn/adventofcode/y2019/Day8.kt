package se.johannalynn.adventofcode.y2019

import java.util.*

object Day8 {

    @JvmStatic
    fun main(args: Array<String>) {
        val pre = false
        val scanner = Day.start(8, pre)

        var width = 25
        var height = 6
        if(pre) {
            width = 3
            height = 2
        }

        star1(scanner, width, height)
        // Too low: 2352
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