package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day4 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(4, false)

        // star1(scanner)
        star2(scanner)
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

    private fun checkPassport2(passport: List<String>): Boolean {
        val fields = mutableMapOf("byr" to false, "iyr" to false, "eyr" to false, "hgt" to false, "hcl" to false, "ecl" to false, "pid" to false, "cid" to false)
        passport.forEach {
            val field = it.substring(0,3)
            val value = it.substring(4).trim()
            // println("$field => $value")
            val valid = when(field) {
                "cid" -> true
                "byr" -> Integer.parseInt(value) in 1920..2002
                "iyr" -> Integer.parseInt(value) in 2010..2020
                "eyr" -> Integer.parseInt(value) in 2020..2030
                "hgt" -> {
                    val hgt = value.substring(0, value.length - 2)
                    val unit = value.substring(value.length - 2)
                    if (unit == "cm") {
                        Integer.parseInt(hgt) in 150..193
                    } else if (unit == "in") {
                        Integer.parseInt(hgt) in 59..76
                    } else {
                        false
                    }
                }
                "hcl" -> Regex("#[0-9a-f]{6}").containsMatchIn(value)
                "ecl" -> value == "amb" || value == "blu" || value == "brn" || value == "gry" || value == "grn" || value == "hzl" || value == "oth"
                "pid" -> Regex("[\\d]{9}").containsMatchIn(value) && value.length == 9
                else -> false
            }
            fields[field] = valid
        }
        return fields.filter { it.key != "cid" }.filter { it.value }.size == 7
    }

    private fun star2(scanner: Scanner) {
        var count = 0
        scanner.useDelimiter("\\n\\n")
        while (scanner.hasNext()) {
            val passport = scanner.next()
            //println("PASSPORT: $passport")
            val parts = passport.split(' ','\n')
            //println(parts.size)
            if(checkPassport2(parts)) {
                count++
            }
        }
        println("valid: $count")
    }

}