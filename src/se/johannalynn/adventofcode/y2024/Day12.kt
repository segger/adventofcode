package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*

object Day12 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(12, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {

        val garden = mutableMapOf<Pair<Int, Int>, Plant>()
        var row = 0
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split("").filter { it.isNotBlank() }.forEachIndexed { col, s ->
                garden[Pair(row, col)] = Plant(s)
            }
            row++
        }

        var region = 1
        garden.forEach { (t, u) ->
            if(u.region == null) {
                u.region = region++
                regions(garden, t, u.region!!)
            }
            var border = 0
            val up = Pair(t.first-1, t.second)
            val down = Pair(t.first+1, t.second)
            val left = Pair(t.first, t.second-1)
            val right = Pair(t.first, t.second+1)

            if (garden[up] == null) {
                border++
            } else if (garden[up]!!.plant != u.plant) {
                border++
            }
            if (garden[down] == null) {
                border++
            } else if (garden[down]!!.plant != u.plant) {
                border++
            }
            if (garden[left] == null) {
                border++
            } else if (garden[left]!!.plant != u.plant) {
                border++
            }
            if (garden[right] == null) {
                border++
            } else if (garden[right]!!.plant != u.plant) {
                border++
            }

            u.borders = border
        }

        val sum = garden.asSequence().groupBy { it.value.region }
            .map { it.value.size * it.value.map { it.value.borders }.sum() }
            .sum()
        println(sum)
    }

    private fun regions(garden: MutableMap<Pair<Int, Int>, Plant>, t: Pair<Int, Int>, region: Int) {
        val plant = garden[t]!!.plant

        val up = Pair(t.first-1, t.second)
        val down = Pair(t.first+1, t.second)
        val left = Pair(t.first, t.second-1)
        val right = Pair(t.first, t.second+1)

        if (garden[up] != null && garden[up]?.plant == plant && garden[up]?.region == null) {
            garden[up]!!.region = region
            regions(garden, up, region)
        }

        if (garden[down] != null && garden[down]?.plant == plant && garden[down]?.region == null) {
            garden[down]!!.region = region
            regions(garden, down, region)
        }

        if (garden[left] != null && garden[left]?.plant == plant && garden[left]?.region == null) {
            garden[left]!!.region = region
            regions(garden, left, region)
        }

        if (garden[right] != null && garden[right]?.plant == plant && garden[right]?.region == null) {
            garden[right]!!.region = region
            regions(garden, right, region)
        }
    }

    data class Plant(val plant: String) {
        var region: Int? = null
        var borders: Int = -1

        override fun toString(): String {
            return "Plant(plant=$plant, region=$region, borders=$borders)"
        }
    }

    private fun star2(scanner: Scanner) {

        val garden = mutableMapOf<Pair<Int, Int>, Plant>()
        var row = 0
        while (scanner.hasNextLine()) {
            scanner.nextLine().split("").filter { it.isNotBlank() }.forEachIndexed { col, s ->
                garden[Pair(row, col)] = Plant(s)
            }
            row++
        }

        var region = 1
        garden.forEach { (t, u) ->
            if(u.region == null) {
                u.region = region++
                regions(garden, t, u.region!!)
            }

            var border = 0

            val up = Pair(t.first-1, t.second)
            val upleft = Pair(t.first-1, t.second-1)
            val left = Pair(t.first, t.second-1)
            val downleft = Pair(t.first+1, t.second-1)
            val down = Pair(t.first+1, t.second)
            val downright = Pair(t.first+1, t.second+1)
            val right = Pair(t.first, t.second+1)
            val upright = Pair(t.first-1, t.second+1)

            val hasup = garden[up] != null && garden[up]!!.plant == u.plant //&& garden[up]!!.region == u.region
            val hasupleft = garden[upleft] != null && garden[upleft]!!.plant == u.plant //&& garden[upleft]!!.region == u.region
            val hasleft = garden[left] != null && garden[left]!!.plant == u.plant //&& garden[left]!!.region == u.region
            val hasdownleft = garden[downleft] != null && garden[downleft]!!.plant == u.plant //&& garden[downleft]!!.region == u.region
            val hasdown = garden[down] != null && garden[down]!!.plant == u.plant //&& garden[down]!!.region == u.region
            val hasdownright = garden[downright] != null && garden[downright]!!.plant == u.plant //&& garden[downright]!!.region == u.region
            val hasright = garden[right] != null && garden[right]!!.plant == u.plant //&& garden[right]!!.region == u.region
            val hasupright = garden[upright] != null && garden[upright]!!.plant == u.plant //&& garden[upright]!!.region == u.region

            val topleft = (!hasup && !hasupleft && !hasleft) || (hasup && hasleft && !hasupleft) || (!hasleft && !hasup && hasupleft)
            val bottomleft = (!hasdown && !hasdownleft && !hasleft) || (hasdown && hasleft && !hasdownleft) || (!hasleft && !hasdown && hasdownleft)
            val topright = (!hasup && !hasupright && !hasright) || (hasup && hasright && !hasupright) || (!hasright && !hasup && hasupright)
            val bottomright = (!hasdown && !hasdownright && !hasright) || (hasdown && hasright && !hasdownright) || (!hasright && !hasdown && hasdownright)

            if (topleft) {
                border++
            }
            if (bottomleft) {
                border++
            }
            if (topright) {
                border++
            }
            if (bottomright) {
                border++
            }
            u.borders = border

        }

        val sum = garden.asSequence().groupBy { it.value.region }
            .map { it.value.size * it.value.map { it.value.borders }.sum() }
            .sum()
        println(sum)
        // 968611 - too low
    }
}
