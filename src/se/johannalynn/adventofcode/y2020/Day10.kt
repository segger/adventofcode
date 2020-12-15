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

        star1(scanner)
        // star2(scanner)
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

    private fun star2(scanner: Scanner) {

    }
}