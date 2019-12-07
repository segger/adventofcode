package se.johannalynn.adventofcode.y2019

import java.util.*

object Day4 {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Day4")

        // 372304-847060
        stars(372304,847060, true)
    }

    fun stars(start: Int, stop: Int, star2: Boolean) {
        var count = 0
        for(i in start..stop) {
            if (star2) {
                if(test2(i.toString())) {
                    count++
                }
            } else {
                if(test1(i.toString())) {
                    count++
                }
            }
        }
        println(count)
    }

    fun test2(nbr: String): Boolean {
        val password = nbr.map { it.toString().toInt() }.toIntArray()
        val tmp = IntArray(10)
        var last = password[0]
        tmp[last]++
        for(i in 1..5) {
            if (password[i] >= last) {
                last = password[i]
                tmp[last]++
            } else {
                return false
            }
        }
        return tmp.find { it == 2 } != null
    }

    fun test1(nbr: String): Boolean {
        val password = nbr.map { it.toString().toInt() }.toIntArray()
        var same = false
        var last = password[0]
        for(i in 1..5) {
            if (password[i] >= last) {
                if (password[i] == last) {
                    same = true
                }
                last = password[i]
            } else {
                return false
            }
        }
        return same
    }
}