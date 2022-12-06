package se.johannalynn.adventofcode.y2022

import se.johannalynn.adventofcode.y2022.Day.start
import java.util.*

object Day6 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(6, false)

        star(scanner, 14)
    }

    /**
     * mjqjpqmgbljsphdztnvjfqwrcgsmlb: first marker after character 7, 19
     * bvwbjplbgvbhsrlpgdmjqwftvncz: first marker after character 5, 23
    nppdvjthqldpwncqszvftbrmjlhg: first marker after character 6, 23
    nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg: first marker after character 10, 29
    zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw: first marker after character 11, 26
     */

    private fun star(scanner: Scanner, marker: Int) {
        while(scanner.hasNextLine()) {
            val input = scanner.nextLine()
            println(buffer(input, marker))
        }
    }

    private fun buffer(input: String, marker: Int): Int {
        for (i in 0 until input.length - marker) {
            val packet = input.substring(i, i+marker)
            val unique = packet.all(hashSetOf<Char>()::add)
            if (unique) {
                return i + marker
            }
        }
        return -1
    }
}