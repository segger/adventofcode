package se.johannalynn.adventofcode.y2023

import java.util.*

object Day2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(2, false)

        //star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        // only 12 red cubes, 13 green cubes, and 14 blue cubes
        var sumValidGameIds = 0
        val map = mapOf("red" to 12, "green" to 13, "blue" to 14)
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine().split(":")
            val gameId = input[0].trim().split(" ")[1].toInt()
            val sets = input[1].split(";")
            var validGame = true
            sets.forEach draw@ {set ->
                val colors = set.split(",")
                colors.forEach {color ->
                    val colorPart = color.trim().split(" ")
                    val nbr = colorPart[0].trim().toInt()
                    if (nbr > map[colorPart[1].trim()]!!) {
                        validGame = false
                        return@draw
                    }
                }
            }
            if (validGame) {
                sumValidGameIds += gameId
            }
        }
        println(sumValidGameIds)
    }

    private fun star2(scanner: Scanner) {
        var sumProductOfMinSets = 0
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine().split(":")
            val gameId = input[0].trim().split(" ")[1].toInt()
            val sets = input[1].split(";")

            val colorsOfSet = mutableMapOf("red" to 0, "blue" to 0, "green" to 0)
            sets.forEach {set ->
                val colors = set.split(",")
                colors.forEach { nbrColor ->
                    val colorPart = nbrColor.trim().split(" ")
                    val nbr = colorPart[0].trim().toInt()
                    val color = colorPart[1].trim()
                    if (nbr > colorsOfSet[color]!!) {
                        colorsOfSet[color] = nbr
                    }
                }
            }
            sumProductOfMinSets += colorsOfSet.values.reduce(Int::times)
        }
        println(sumProductOfMinSets)
    }
}