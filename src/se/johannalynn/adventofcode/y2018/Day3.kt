package se.johannalynn.adventofcode.y2018

import java.io.File
import java.util.*

object Day3 {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Day3")

        val inFileName = "2018/input/day3_pre1.txt"
        val scanner = Scanner(File(inFileName))

        //star1(scanner)
        star2(scanner)
    }

    data class Elf(val id: Int, val left: Int, val top: Int, val width: Int, val height: Int)

    private fun getElves(scanner: Scanner): List<Elf> {
        val elves = ArrayList<Elf>()
        while(scanner.hasNextLine()) {
            val elf = scanner.nextLine().split(" ")

            val id = elf[0].substring(1).toInt()
            val pos = elf[2].substring(0, elf[2].length - 1)
            val position = pos.split(",")
            val left = position[0].toInt()
            val top = position[1].toInt()
            val dimension = elf[3].split("x")
            val width = dimension[0].toInt()
            val height = dimension[1].toInt()

            val newElf = Elf(id, left, top, width, height)
            elves.add(newElf)
        }
        return elves
    }

    private fun star1(scanner: Scanner) {
        var fabric = Array(1000) { IntArray(1000) }

        var count = 0
        val elves = getElves(scanner)
        for(elf in elves) {
            for(i in IntProgression.fromClosedRange(elf.left, elf.left + elf.width - 1, 1)) {
                for(j in IntProgression.fromClosedRange(elf.top, elf.top + elf.height - 1, 1)) {
                    val square = fabric[i][j]
                    if (square == 0) {
                        fabric[i][j]++
                    } else if(square == 1) {
                        count++
                        fabric[i][j]++
                    }
                }
            }
        }
        println(count)
    }

    private fun star2(scanner: Scanner) {
        var fabric = Array(1000) { IntArray(1000) }
        val elves = getElves(scanner)

        for(elf in elves) {
            for(i in elf.left until elf.left + elf.width) {
                for(j in elf.top until elf.top + elf.height) {
                    fabric[i][j]++
                }
            }
        }

        for(elf in elves) {
            var claimed = false
            for(i in elf.left until elf.left + elf.width) {
                for(j in elf.top until elf.top + elf.height) {
                    if(fabric[i][j] != 1) {
                        claimed = true
                    }
                }
            }

            if(!claimed) {
                println(elf.id)
            }
        }

    }
}