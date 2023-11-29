package se.johannalynn.adventofcode.y2018

import java.io.File
import java.io.FileNotFoundException
import java.util.ArrayList
import java.util.Scanner

object Day3_converted {

    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        println("Day3_java")

        // #1 @ 1,3: 4x4
        val inFileName = "input/2018/day3.txt"
        val scanner = Scanner(File(inFileName))

        //star1(scanner);
        star2(scanner)
    }

    internal class Elf(var id: Int, var left: Int, var top: Int, var width: Int, var height: Int)

    private fun getElves(scanner: Scanner): List<Elf> {
        val elves = ArrayList<Elf>()
        while (scanner.hasNextLine()) {
            val elf = scanner.nextLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val id = Integer.valueOf(elf[0].substring(1))
            val pos = elf[2].substring(0, elf[2].length - 1)
            val position = pos.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val left = Integer.valueOf(position[0])
            val top = Integer.valueOf(position[1])
            val dimension = elf[3].split("x".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val width = Integer.valueOf(dimension[0])
            val height = Integer.valueOf(dimension[1])

            val newElf = Elf(id, left, top, width, height)
            elves.add(newElf)

        }
        return elves
    }

    private fun star1(scanner: Scanner) {
        val fabric = Array(1000) { IntArray(1000) }

        var count = 0
        val elves = getElves(scanner)
        for (elf in elves) {
            for (i in elf.left until elf.left + elf.width) {
                for (j in elf.top until elf.top + elf.height) {
                    val square = fabric[i][j]
                    if (square == 0) {
                        fabric[i][j]++
                    } else if (square == 1) {
                        count++
                        fabric[i][j]++
                    }
                }
            }
        }

        println(count)

    }

    private fun star2(scanner: Scanner) {
        val fabric = Array(1000) { IntArray(1000) }
        val elves = getElves(scanner)

        for (elf in elves) {
            for (i in elf.left until elf.left + elf.width) {
                for (j in elf.top until elf.top + elf.height) {
                    fabric[i][j]++
                }
            }
        }
        for (elf in elves) {
            var claimed = false
            for (i in elf.left until elf.left + elf.width) {
                for (j in elf.top until elf.top + elf.height) {
                    val square = fabric[i][j]
                    if (square != 1) {
                        claimed = true
                    }
                }
            }
            if (!claimed) {
                println(elf.id)
            }
        }
    }
}
