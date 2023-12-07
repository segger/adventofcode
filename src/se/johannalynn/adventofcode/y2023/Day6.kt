package se.johannalynn.adventofcode.y2023

import se.johannalynn.adventofcode.y2023.Day.start
import java.util.*

object Day6 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(6, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var product = 1
        while (scanner.hasNextLine()) {
            val time = scanner.nextLine().split(":")[1].split(" ").filter { it.isNotBlank() }.map { it.toInt() }
            val distance = scanner.nextLine().split(":")[1].split(" ").filter { it.isNotBlank() }.map { it.toInt() }

            time.forEachIndexed { idx, ms ->
                var firstFound = false
                var firstIdx = 0
                while (!firstFound && firstIdx < ms) {
                    firstIdx++
                    val dist = (ms - firstIdx) * firstIdx
                    if (dist > distance[idx]) {
                        firstFound = true
                    }
                }

                var lastFound = false
                var lastIdx = ms
                while (!lastFound && lastIdx > 0) {
                    lastIdx--
                    val dist = (ms - lastIdx) * lastIdx
                    if (dist > distance[idx]) {
                        lastFound = true
                    }
                }

                val ways = lastIdx - firstIdx + 1
                // println("idx $idx - $ways")
                product *= ways
            }
        }
        println(product)
    }

    private fun star2(scanner: Scanner) {
        while (scanner.hasNextLine()) {
            val time = scanner.nextLine().split(":")[1].replace(" ","").toLong()
            val distance = scanner.nextLine().split(":")[1].replace(" ","").toLong()

            var firstFound = false
            var firstIdx = 0
            while (!firstFound && firstIdx < time) {
                firstIdx++
                val dist = (time - firstIdx) * firstIdx
                if (dist > distance) {
                    firstFound = true
                }
            }

            var lastFound = false
            var lastIdx = time
            while (!lastFound && lastIdx > 0) {
                lastIdx--
                val dist = (time - lastIdx) * lastIdx
                if (dist > distance) {
                    lastFound = true
                }
            }

            val ways = lastIdx - firstIdx + 1
            println(ways)
        }
    }
}
