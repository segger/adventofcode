package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day7 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(7, false)

        star1(scanner)
        // star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val bags = mutableMapOf<String, Bag>()
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine()
            val row = input.split(" contain ")
            val name = row[0].trim().substringBefore(" bag").trim()
            // println("BAG: ${row[0]} -> $name")

            // println("bags: ${row[1]}")
            val containBags = row[1].split(",")
            // println(bags)
            containBags.forEach {
                val color = it.trim().substringAfter(" ").substringBefore(" bag").trim()
                // println(color)
                if (color != "other") {
                    var bag = bags[color]
                    if(bag == null) {
                        bag = Bag(color)
                    }
                    bag.parents.add(name)
                    bags[color] = bag
                }
            }
        }
        /*
        bags.forEach {
            println(it.key)
            println(it.value.parents.size)
        } */
        val parents = calc("shiny gold", bags)
        println(parents.size)
    }

    private fun calc(name: String, bags: Map<String, Bag>): Set<String> {
        val bag = bags[name]
        val parents = mutableSetOf<String>()

        bag?.parents?.forEach {
            parents.add(it)
            parents.addAll(calc(it, bags))
        }

        return parents
    }

    private fun star2(scanner: Scanner) {

    }

    class Bag(name: String) {
        val parents = mutableSetOf<String>()
    }
}