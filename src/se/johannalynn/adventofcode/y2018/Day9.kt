package se.johannalynn.adventofcode.y2018

import java.util.*

object Day9 {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Day 9")
        // 419 players; last marble is worth 72164 points

        // 9 players; last marble is worth 25 points: high score is 32
        // 10 players; last marble is worth 1618 points: high score is 8317
        // 13 players; last marble is worth 7999 points: high score is 146373
        // 17 players; last marble is worth 1104 points: high score is 2764
        // 21 players; last marble is worth 6111 points: high score is 54718
        // 30 players; last marble is worth 5807 points: high score is 37305

        val arrays = arrayOf(
            //arrayOf(9, 25, 32),
            //arrayOf(10, 1618, 8317),
            //arrayOf(13, 7999, 146373),
            //arrayOf(17, 1104, 2764),
            //arrayOf(21, 6111, 54718),
            //arrayOf(30, 5807, 37305),
            //arrayOf(419, 72164, 0),
            arrayOf(419, 72164, 0)
        )

        for (input in arrays) {
            //val score = star1(input)
            //println("" + score + " = " + input[2])
        }

        star2(419, 7216400)
    }

    private fun star1(input : Array<Int>): Int {
        val players = input[0]
        val maxMarble = input[1]
        val circle = IntArray(maxMarble)
        circle[0] = 0
        circle[1] = 1
        var currentIdx = 1
        var size = 2
        var player = 1
        val playerScore = HashMap<Int, Int>()
        for(marble in 2..maxMarble) {
            player = (++player) % players

            if(marble % 23 == 0) {
                var score = playerScore.get(player)
                if(score == null) {
                    score = 0
                }
                var counterMarbleIdx = currentIdx - 7
                if (counterMarbleIdx < 0) {
                    counterMarbleIdx += size
                }
                val counterScore = circle[counterMarbleIdx]
                val marbleScore = marble
                score += marbleScore + counterScore
                playerScore.put(player, score)
                // move
                for (idx in counterMarbleIdx until (size - 1)) {
                    circle[idx] = circle[idx + 1]
                }
                currentIdx = counterMarbleIdx
                size--
            } else {
                val moveIdx = (currentIdx + 2) % size
                if (moveIdx == 0) {
                    circle[size] = marble
                    currentIdx = size
                } else {
                    // move
                    for (idx in (size - 1) downTo moveIdx) {
                        circle[idx + 1] = circle[idx]
                    }
                    circle[moveIdx] = marble
                    currentIdx = moveIdx
                }
                size++
            }

            // print("[" + player + "]")
            // circle.forEach { print(" " + it) }
            // println()
        }

        var maxScore = 0
        val itr = playerScore.keys.iterator()
        while(itr.hasNext()) {
            val player = itr.next()
            val score = playerScore.get(player)
            if (score!! > maxScore) {
                maxScore = score
            }
        }
        return maxScore
    }

    private fun star2(players: Int, maxMarble: Int): Int {

        val circle = IntArray(maxMarble)
        circle[0] = 0
        circle[1] = 1
        var currentIdx = 1
        var size = 2
        var player = 1
        val playerScore = HashMap<Int, Int>()
        for(marble in 2..maxMarble) {
            player = (++player) % players

            if(marble % 23 == 0) {
                var score = playerScore.get(player)
                if(score == null) {
                    score = 0
                }
                var counterMarbleIdx = currentIdx - 7
                if (counterMarbleIdx < 0) {
                    counterMarbleIdx += size
                }
                val counterScore = circle[counterMarbleIdx]
                val marbleScore = marble
                score += marbleScore + counterScore
                playerScore.put(player, score)
                // move
                for (idx in counterMarbleIdx until (size - 1)) {
                    circle[idx] = circle[idx + 1]
                }
                currentIdx = counterMarbleIdx
                size--
            } else {
                val moveIdx = (currentIdx + 2) % size
                if (moveIdx == 0) {
                    circle[size] = marble
                    currentIdx = size
                } else {
                    // move
                    for (idx in (size - 1) downTo moveIdx) {
                        circle[idx + 1] = circle[idx]
                    }
                    circle[moveIdx] = marble
                    currentIdx = moveIdx
                }
                size++
            }

            // print("[" + player + "]")
            // circle.forEach { print(" " + it) }
            // println()
        }

        var maxScore = 0
        val itr = playerScore.keys.iterator()
        while(itr.hasNext()) {
            val player = itr.next()
            val score = playerScore.get(player)
            if (score!! > maxScore) {
                maxScore = score
            }
        }
        return maxScore
    }
}