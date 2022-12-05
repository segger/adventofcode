package se.johannalynn.adventofcode.y2022

import se.johannalynn.adventofcode.y2022.Day.start
import java.util.*

object Day5 {

    @JvmStatic
    fun main(args: Array<String>) {
        val pre = false
        val scanner = start(5, pre)

        // star1(scanner, if (pre) 3 else 9)
        star2(scanner, if (pre) 3 else 9)
    }

    private fun star1(scanner: Scanner, nbrOfStacks: Int) {
        var instructions = false
        val stacks = Array<ArrayList<String>>(nbrOfStacks) {
            arrayListOf()
        }

        while (scanner.hasNextLine()) {
            val input = scanner.nextLine()
            if (instructions) {
                val instruction = input.split(" ")
                val amount = instruction[1].toInt()
                val from = instruction[3].toInt()
                val to = instruction[5].toInt()

                val move = stacks[from-1].subList(0, amount)
                stacks[to-1].addAll(0, move.reversed())
                stacks[from-1].subList(0, amount).clear()

                /*
                stacks.forEach {
                    it.forEach {
                        print("$it ")
                    }
                    println()
                } */
            } else {
                // row with 1 2 3
                if (input[1] == '1') {
                    instructions = true
                    scanner.nextLine()

                    /*
                    stacks.forEach {
                        //it.reverse()
                        it.forEach {
                            print("$it ")
                        }
                        println()
                    }*/
                }
                if (input.length < (nbrOfStacks * 4)) {
                    input.padEnd((nbrOfStacks * 4), ' ').chunked(4).forEachIndexed { index, crate ->
                        if (crate.startsWith("[")) {
                            stacks[index].add(crate.trim().removePrefix("[").removeSuffix("]"))
                        }
                    }
                }
            }
        }

        stacks.forEach {
            print(it.first())
        }
    }

    private fun star2(scanner: Scanner, nbrOfStacks: Int) {
        var instructions = false
        val stacks = Array<ArrayList<String>>(nbrOfStacks) {
            arrayListOf()
        }

        while (scanner.hasNextLine()) {
            val input = scanner.nextLine()
            if (instructions) {
                val instruction = input.split(" ")
                val amount = instruction[1].toInt()
                val from = instruction[3].toInt()
                val to = instruction[5].toInt()

                val move = stacks[from-1].subList(0, amount)
                stacks[to-1].addAll(0, move)
                stacks[from-1].subList(0, amount).clear()

                /*
                stacks.forEach {
                    it.forEach {
                        print("$it ")
                    }
                    println()
                } */
            } else {
                // row with 1 2 3
                if (input[1] == '1') {
                    instructions = true
                    scanner.nextLine()

                    /*
                    stacks.forEach {
                        //it.reverse()
                        it.forEach {
                            print("$it ")
                        }
                        println()
                    }*/
                }
                if (input.length < (nbrOfStacks * 4)) {
                    input.padEnd((nbrOfStacks * 4), ' ').chunked(4).forEachIndexed { index, crate ->
                        if (crate.startsWith("[")) {
                            stacks[index].add(crate.trim().removePrefix("[").removeSuffix("]"))
                        }
                    }
                }
            }
        }

        stacks.forEach {
            print(it.first())
        }
    }
}