package se.johannalynn.adventofcode.y2018

import java.util.*

object Day8 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(8, false)

        star1(scanner)
    }

    private fun readNode(scanner: Scanner): Int {
        var metadata = 0
        val nbrOfChildren = scanner.nextInt()
        val nbrOfMetadata = scanner.nextInt()
        //println("Node " + nbrOfChildren + ", " + nbrOfMetadata)

        repeat(nbrOfChildren) {
            metadata += readNode(scanner)
        }

        repeat(nbrOfMetadata) {
            val metadataValue = scanner.nextInt()
            //println("metadataValue " + metadataValue)
            metadata += metadataValue
        }

        return metadata
    }

    private fun star1(scanner: Scanner) {
        val sum = readNode(scanner)
        println(sum)
    }
}