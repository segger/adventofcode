package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*

object Day9 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(9, false)

        // star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {

        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split("").filter { it.isNotBlank() }.map { it.toLong() }
            // println("last ${next[next.size-3]} ${next[next.size-2]} ${next[next.size-1]}")
            var topId = next.size / 2
            var id = 0
            val size = next.sumOf { it }.toInt()
            var checksum = 0L

            var pos = 0
            var topPos = size - 1L
            var topIdx = next.size - 1
            if (topIdx % 2 != 0) {
                // end with free space info
                topIdx--
            }

            var idx = 0
            var currNext = next[idx]
            var currNextTop = next[topIdx]

            println("idx $idx, topIdx $topIdx, currNext $currNext, currNextTop $currNextTop")

            var done = false
            while (idx <= topIdx && !done) {
                if (idx % 2 == 0) {
                    // println("file $idx, currNext $currNext")
                    for (i in 0 until currNext) {
                        if (pos > topPos) {
                            done = true
                        } else {
                            checksum += (pos++ * id)
                            // println("checksum $checksum, pos $pos, id $id $topPos")
                        }
                    }
                    id++
                } else {
                    // println("space $idx, currNext $currNext, currNextTop $currNextTop")
                    for (j in 0 until currNext) {
                        if (currNextTop > 0) {
                            if (pos > topPos) {
                                done = true
                            } else {
                                checksum += (pos++ * topId)
                                currNextTop--
                                topPos--
                                // println("checksum $checksum, pos $pos, topId $topId, topPos $topPos")
                            }
                        } else {
                            if (topIdx % 2 == 0) {
                                topPos -= next[--topIdx]
                            }
                            currNextTop = next[--topIdx]
                            topId--
                            // println("new $currNextTop, topIdx $topIdx, topId $topId")

                            if (pos > topPos) {
                                done = true
                            } else {
                                checksum += (pos++ * topId)
                                topPos--
                                currNextTop--
                                // println("checksum $checksum, pos $pos, topId $topId, currNextTop $currNextTop, topPos $topPos")
                            }
                        }
                    }
                }
                currNext = next[++idx]
                // currNextTop = next[topIdx]
            }
            println("pos $pos, topPos $topPos, id $id, topId $topId, currNextTop $currNextTop, currNext $currNext")

            println(checksum)
            // too high: 6201390809186
        }
    }

    private fun star2(scanner: Scanner) {

        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split("").filter { it.isNotBlank() }.map { it.toInt() }
            val size = next.sumOf { it }
            val memory = IntArray(size)
            val input = mutableMapOf<Int, Block>()
            // val input = next.map { Block(-1, -1, it) }

            var id = 0
            var pos = 0
            for (i in 0 until next.size) {
                for (j in 0 until next[i]) {
                    if (i % 2 == 0) {
                        memory[pos++] = id
                    } else {
                        memory[pos++] = -1
                    }
                }
                if (i % 2 == 0) {
                    input[i] = Block(id, pos - next[i],next[i])
                    id++
                } else {
                    input[i] = Block(-1, pos - next[i], next[i])
                }
            }

            /*
            input.forEach {
                println("id ${it.key} startIdx ${it.value.startIdx} size ${it.value.size} id ${it.value.id}")
            } */

            /*
            var topIdx = next.size - 1
            if (topIdx % 2 != 0) {
                topIdx--
            } */

            for (topIdx in next.size - 1 downTo 0 step 2) {
                var idx = 0
                var moved = false
                while(!moved && idx < topIdx) {
                    if (idx % 2 == 0) {

                    } else {
                        val topFileSize = next[topIdx]
                        val space = input[idx]!!.size
                        if (space >= topFileSize) {
                            // println("space $space, topFileSize $topFileSize")
                            moved = true
                            input[topIdx]!!.startIdx = input[idx]!!.startIdx

                            input[idx]!!.size = space - topFileSize
                            input[idx]!!.startIdx += topFileSize
                        }
                    }
                    idx++
                }
            }

            val files = input.filter { it.value.id != -1 }.map {
                it.value.startIdx to Pair(it.value.id, it.value.size)
            }.toMap()

            /*
            files.toSortedMap().forEach {
                println("startIdx ${it.key} id ${it.value.first} size ${it.value.second}")
            } */

            var checksum = 0L
            for(i in 0 until size) {
                // println("i $i")
                files[i]?.let {
                    for (j in 0..it.second - 1) {
                        checksum += (i+j) * it.first
                        // println("checksum $checksum i+j ${i+j} id: ${it.first}")
                    }
                }
            }
            println("checksum $checksum")
        }
    }

    data class Block(var id: Int, var startIdx: Int, var size: Int)
    // data class Space(val startIdx: Int, val size: Int)
}
