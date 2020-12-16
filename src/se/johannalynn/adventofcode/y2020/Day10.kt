package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*
import kotlin.math.abs

object Day10 {

    /*
    16
10
15
5
1
11
7
19
6
12
4
     */

    /*
    28
33
18
42
31
14
46
20
48
47
24
23
49
45
19
38
39
11
1
32
25
35
8
17
7
9
4
2
34
10
3
     */

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(10, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val adapters = mutableListOf<Int>()
        while (scanner.hasNextLine()) {
            val input = Integer.parseInt(scanner.nextLine())
            adapters.add(input)
        }
        adapters.sort()
        var ones = 0
        var twos = 0
        var threes = 0
        var jolt = 0
        adapters.forEachIndexed { idx, value ->
            val diff = abs(jolt - adapters[idx])
            // println("${jolt} - ${adapters[idx]} -> $diff")
            when(diff) {
                1 -> ones++
                2 -> twos++
                3 -> threes++
            }
            jolt = adapters[idx]
        }
        threes++
        // println("ones: $ones, threes: $threes")
        val tot = ones * threes
        println(tot)
    }

    private fun star2desc(scanner: Scanner) {
        var adapters = mutableListOf<Int>()
        while (scanner.hasNextLine()) {
            val input = Integer.parseInt(scanner.nextLine())
            adapters.add(input)
        }
        adapters = adapters.sortedDescending().toMutableList()

        val deviceJolt = adapters.first() + 3
        val arrangements = mutableMapOf<Int, Adapter>()
        arrangements[deviceJolt] = Adapter(deviceJolt)
        val orphans = mutableSetOf<Int>()
        orphans.add(deviceJolt)
        adapters.forEach {adapter ->
            orphans.forEach {orphan ->
                // println("$adapter $orphan")
                val diff = abs(adapter - orphan)
                if (diff <= 3) {
                    var tmp = arrangements[adapter]
                    if (tmp == null) {
                        tmp = Adapter(adapter)
                    }
                    tmp.parents.add(orphan)
                    arrangements[adapter] = tmp
                }
            }
            orphans.add(adapter)
            // remove old?
        }
        var sum = 1
        arrangements.forEach {
            val paths = it.value.parents.size
            if (paths > 1) {
                sum += paths
            }
            /*
            println("KEY ${it.key}")
            it.value.parents.forEach {
                print("$it, ")
            }
            println()*/
        }
        println(sum)
    }

    private fun star2(scanner: Scanner) {
        var adapters = mutableListOf<Int>()
        while (scanner.hasNextLine()) {
            val input = Integer.parseInt(scanner.nextLine())
            adapters.add(input)
        }
        adapters = adapters.sortedDescending().toMutableList()
        adapters.add(0)

        val deviceJolt = adapters.first() + 3
        var arrangements = mutableMapOf<Int, Adapter>()
        arrangements[deviceJolt] = Adapter(deviceJolt)
        val orphans = mutableSetOf<Int>()
        orphans.add(deviceJolt)
        adapters.forEach {adapter ->
            orphans.forEach {orphan ->
                // println("$adapter $orphan")
                val diff = abs(adapter - orphan)
                if (diff <= 3) {
                    var tmp = arrangements[adapter]
                    if (tmp == null) {
                        tmp = Adapter(adapter)
                    }
                    tmp.parents.add(orphan)
                    arrangements[adapter] = tmp
                }
            }
            orphans.add(adapter)
            // remove old?
        }

        arrangements = arrangements.toSortedMap()
        val items = mutableMapOf<Int, Long>()
        items.put(arrangements.keys.first(), 1)
        arrangements.forEach {entry ->
            // println("KEY ${entry.key}")
            var nbrOf = items[entry.key]
            if (nbrOf == null) {
                nbrOf = 1L
            }
            entry.value.parents.forEach {
                var value = items[it]
                if (value == null) {
                    value = 0L
                }
                items[it] = value + nbrOf
            }
        }

        items.forEach {
            println("KEY ${it.key} VALUE: ${it.value}")
        }

        val permutations = items[deviceJolt]
        println(permutations)
    }

    class Adapter(jolt: Int) {
        val parents = mutableSetOf<Int>()
    }
}