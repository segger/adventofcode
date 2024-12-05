package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*
import kotlin.collections.LinkedHashSet
import kotlin.math.abs

object Day5 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(5, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {

        var sum = 0
        val allRules = mutableMapOf<Int, MutableList<String>>()
        val updates = mutableListOf<List<Int>>()
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            if (next.contains("|")) {
                val rules = next.split("|")

                val rules0 = mutableListOf(next)
                if (allRules[rules[0].toInt()] != null) {
                    rules0.addAll(allRules[rules[0].toInt()]!!)
                }
                allRules[rules[0].toInt()] = rules0
                val rules1 = mutableListOf(next)
                if (allRules[rules[1].toInt()] != null) {
                    rules1.addAll(allRules[rules[1].toInt()]!!)
                }
                allRules[rules[1].toInt()] = rules1
            } else if (next.contains(",")) {
                val pageNumbers = next.split(",").map { it.toInt() }
                updates.add(pageNumbers)
            }
        }

        updates.forEach { update ->
            var invalid = false
            update.forEachIndexed { index, value ->
                allRules[value]?.forEach { rule ->
                    val splitted = rule.split("|")
                    if (splitted[0].toInt() == value) {
                        if (update.contains(splitted[1].toInt())) {
                            if (update.indexOf(splitted[1].toInt()) < index) {
                                invalid = true
                            }
                        }
                    } else {
                        if (update.contains(splitted[0].toInt())) {
                            if (update.indexOf(splitted[0].toInt()) > index) {
                                invalid = true
                            }
                        }
                    }
                }
            }
            if (!invalid) {
                sum += update[update.size / 2]
            }
        }

        println(sum)
    }

    data class Rule(val from: Int, val to: Int) {

        fun keep(pageNumbers: List<Int>): Boolean {
            var hasFrom = false
            var hasTo = false
            pageNumbers.forEach {
                if (it == from) {
                    hasFrom = true
                }
                if (it == to) {
                    hasTo = true
                }
            }
            return hasFrom && hasTo
        }
    }

    private fun star2(scanner: Scanner) {

        var sum = 0
        val allRules = mutableListOf<Rule>()
        val allRulesMap = mutableMapOf<Int, List<Rule>>()
        val updates = mutableListOf<List<Int>>()
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            if (next.contains("|")) {
                val rules = next.split("|")
                val newRule = Rule(rules[0].toInt(), rules[1].toInt())
                allRules.add(newRule)

                val rules0 = mutableListOf(newRule)
                if (allRulesMap[rules[0].toInt()] != null) {
                    rules0.addAll(allRulesMap[rules[0].toInt()]!!)
                }
                allRulesMap[rules[0].toInt()] = rules0
                val rules1 = mutableListOf(newRule)
                if (allRulesMap[rules[1].toInt()] != null) {
                    rules1.addAll(allRulesMap[rules[1].toInt()]!!)
                }
                allRulesMap[rules[1].toInt()] = rules1
            } else if (next.contains(",")) {
                val pageNumbers = next.split(",").map { it.toInt() }
                updates.add(pageNumbers)
            }
        }

        updates.forEach { update ->
            var invalid = false
            // println("UPDATE $update")
            // check if it's already valid
            update.forEachIndexed { index, value ->
                allRulesMap[value]?.forEach { rule ->
                    if (rule.from == value) {
                        if (update.contains(rule.to)) {
                            if (update.indexOf(rule.to) < index) {
                                invalid = true
                            }
                        }
                    } else {
                        if (update.contains(rule.from)) {
                            if (update.indexOf(rule.from) > index) {
                                invalid = true
                            }
                        }
                    }
                }
            }

            if (invalid) {
                val result = mutableListOf<Int>();
                val currentUpdate = update.toMutableList()
                // println("CURRENT $currentUpdate")
                for (i in 0 until update.size / 2 + 1) {
                    // go through rules get the ones affecting this update
                    val currentRules = allRules.filter { it.keep(currentUpdate) }

                    // get the one with most left rules
                    val maxLeftRules =
                        currentRules.groupBy { it.from }.map { Pair(it.key, it.value.size) }.maxByOrNull { it.second }
                    // put into result
                    result.add(maxLeftRules!!.first)
                    val updateIndex = currentUpdate.indexOf(maxLeftRules.first)
                    currentUpdate.removeAt(updateIndex)
                    // go through rules without the one selected
                }
                sum += result.last()
            }
        }

        println(sum)
    }
}
