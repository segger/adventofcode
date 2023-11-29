package se.johannalynn.adventofcode.y2018

import java.io.File
import java.io.FileNotFoundException
import java.util.HashMap
import java.util.Scanner

object Day2 {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Day2")

        val inFileName = "input/2018/day2.txt"
        val scanner = Scanner(File(inFileName))

        //star1(scanner)
        star2(scanner);
    }

    private fun count(id: String): BooleanArray {
        val retValue = BooleanArray(2)
        val counter = HashMap<Char, Int>()
        for (letter in id.toCharArray()) {
            var current: Int? = counter[letter]
            if (current != null) {
                counter[letter] = ++current
            } else {
                counter[letter] = 1
            }
        }
        val itr = counter.keys.iterator()
        while (itr.hasNext()) {
            val letter = itr.next()
            val nbr = counter[letter]
            if (nbr == 2) {
                retValue[0] = true
            }
            if (nbr == 3) {
                retValue[1] = true
            }
        }
        return retValue
    }

    private fun star1(scanner: Scanner) {
        var twos = 0
        var threes = 0
        while (scanner.hasNextLine()) {
            val id = scanner.nextLine()
            val counts = count(id)
            twos += if (counts[0]) 1 else 0
            threes += if (counts[1]) 1 else 0
        }

        println(twos * threes)
    }

    private fun hash(id: String): Int {
        var sum = 0
        id.toUpperCase().toCharArray().forEachIndexed { index, it ->  sum += ((index+1) * (it.toInt() - 64) ) }
        return sum
    }

    private fun star2(scanner: Scanner) {
        val idList = ArrayList<String>()

        while (scanner.hasNextLine()) {
            val id = scanner.nextLine()
            // val hash = hash(id)
            idList.add(id)
        }

        for (id1 in idList) {
            for (id2 in idList) {

                var idx = 0
                var missmatches = 0
                var missmatchIdx = -1
                while(idx < id1.length) {
                    if(id1[idx] != id2[idx]) {
                        missmatches++
                        missmatchIdx = idx
                    }

                    if(missmatches > 1) {
                        break
                    }

                    idx++
                }

                if (missmatches == 0) {
                    break
                }

                if (missmatches == 1) {
                    println(id1.removeRange(missmatchIdx, missmatchIdx+1))
                }
            }
        }

    }
}
