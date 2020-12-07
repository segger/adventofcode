package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*
import kotlin.math.exp

object Day4 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(4, false)

        star1(scanner)
        // star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var count = 0
        scanner.useDelimiter("\\n\\n")
        while (scanner.hasNext()) {
            val passport = scanner.next()
            //println("PASSPORT: $passport")
            val parts = passport.split(' ','\n')
            //println(parts.size)
            if(checkPassport(parts)) {
                count++
            }
        }
        println("valid: $count")
    }

    private fun checkPassport(passport: List<String>): Boolean {
        val fields = mutableMapOf("byr" to false, "iyr" to false, "eyr" to false, "hgt" to false, "hcl" to false, "ecl" to false, "pid" to false, "cid" to false)
        passport.forEach {
            fields[it.substring(0,3)] = true
        }
        return fields.filter { it.key != "cid" }.filter { it.value }.size == 7
    }

    private fun star2(scanner: Scanner) {

    }

}