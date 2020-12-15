package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*

object Day7 {

    /*
    shiny gold bags contain 2 dark red bags.
dark red bags contain 2 dark orange bags.
dark orange bags contain 2 dark yellow bags.
dark yellow bags contain 2 dark green bags.
dark green bags contain 2 dark blue bags.
dark blue bags contain 2 dark violet bags.
dark violet bags contain no other bags.

light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.
     */

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(7, false)

        // star1(scanner)
        star2(scanner)
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
        val bags = mutableMapOf<String, Bag2>()
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine()
            val row = input.split(" contain ")
            val name = row[0].trim().substringBefore(" bag").trim()
            //println("BAG: ${row[0]} -> $name")

            var bag = bags[name]
            if (bag == null) {
                bag = Bag2(name)
            }
            // println("bags: ${row[1]}")
            val containBags = row[1].split(",")
            // println(bags)
            containBags.forEach {
                val color = it.trim().substringAfter(" ").substringBefore(" bag").trim()
                //println("color: $color")

                if("other" != color) {
                    val nbr = Integer.parseInt(it.trim().substringBefore(" "))
                    //println("nbr: $nbr")
                    bag.bags.add(Pair(nbr, color))
                }
            }
            bags[name] = bag
        }
        /*
        bags.forEach {
            println("BAG ${it.key}")
            println("BAG2 ${it.value.bags.size}")
        } */

        val contains = calc2("shiny gold", bags)
        println(contains)
    }

    private fun calc2(name: String, bags: Map<String, Bag2>): Int {
        val bag = bags[name] ?: return 0
        if (bag.bags.isEmpty()) return 0
        var sum = 0
        bag.bags.forEach {
            //println("${it.first} ${it.second}")
            val children = it.first + it.first * calc2(it.second, bags)
            // println("$name - children $children")
            sum += children
        }
        return sum
    }

    class Bag(name: String) {
        val parents = mutableSetOf<String>()
    }

    class Bag2(name: String) {
        val bags = mutableListOf<Pair<Int, String>>()
    }
}