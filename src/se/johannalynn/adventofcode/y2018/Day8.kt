package se.johannalynn.adventofcode.y2018

import java.util.*

object Day8 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(8, false)

        //star1(scanner)
        star2(scanner)
    }

    private fun readMetadataSum(scanner: Scanner): Int {
        var metadata = 0
        val nbrOfChildren = scanner.nextInt()
        val nbrOfMetadata = scanner.nextInt()
        //println("Node " + nbrOfChildren + ", " + nbrOfMetadata)

        repeat(nbrOfChildren) {
            metadata += readMetadataSum(scanner)
        }

        repeat(nbrOfMetadata) {
            val metadataValue = scanner.nextInt()
            //println("metadataValue " + metadataValue)
            metadata += metadataValue
        }

        return metadata
    }

    private fun star1(scanner: Scanner) {
        val sum = readMetadataSum(scanner)
        println(sum)
    }

    data class Node(val value: Int, val children: List<Node>)

    private fun readRefValue(scanner: Scanner): Node {
        val nbrOfChildren = scanner.nextInt()
        val nbrOfMetadata = scanner.nextInt()

        val children = ArrayList<Node>()
        for(idx in 1..nbrOfChildren) {
            val childNode = readRefValue(scanner)
            children.add(childNode)
        }

        var metadata = 0
        if(nbrOfChildren == 0) {
            repeat(nbrOfMetadata) {
                val metadataValue = scanner.nextInt()
                metadata += metadataValue
            }
        } else {
            repeat(nbrOfMetadata) {
                val metadataRef = scanner.nextInt() - 1
                var metadataRefValue = 0
                if(metadataRef < children.size) {
                    metadataRefValue = children.get(metadataRef).value
                }
                metadata += metadataRefValue
            }
        }
        return Node(metadata, children)
    }

    private fun star2(scanner: Scanner) {
        val rootNode = readRefValue(scanner)
        println(rootNode.value)
    }
}