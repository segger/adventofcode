package se.johannalynn.adventofcode.y2018

import java.io.File
import java.io.FileNotFoundException
import java.util.HashMap
import java.util.Scanner

object Day2 {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Day2")

        val inFileName = "2018/input/day2_pre2.txt"
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

    private fun star2(scanner: Scanner) {
        
    }
}
