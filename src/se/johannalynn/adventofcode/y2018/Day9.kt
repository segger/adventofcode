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
            //arrayOf(9, 25, 32)
            //arrayOf(10, 1618, 8317)
            //arrayOf(13, 7999, 146373),
            //arrayOf(17, 1104, 2764),
            //arrayOf(21, 6111, 54718),
            //(arrayOf(30, 5807, 37305),
            //arrayOf(419, 72164, 423717),
            arrayOf(419, 7216400, 0)
        )


        // too low 1757945027
        // correct 3553108197

        //star2(419, 7216400)

        /*
        for (input in arrays) {
            val start = System.currentTimeMillis()
            val score = star2(input)
            println("" + score + " = " + input[2])
            val stop = System.currentTimeMillis()
            println("It took " + (stop-start) + "ms")
        }*/

        for (input in arrays) {
            val score = star2b(input)
            println("" + score + " = " + input[2])
        }

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

                print("[" + player + "]")
                circle.forEach { print(" " + it) }
                println()
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

            //print("[" + player + "]")
            //circle.forEach { print(" " + it) }
            //println()
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

    private fun star2(input : Array<Int>): Int {
        val players = input[0]
        val maxMarble = input[1]
        val itrNbr = maxMarble / 23

        val board = mutableListOf(0, 16,  8, 17, 4, 18, 9, 19, 2, 20, 10, 21, 5, 22, 11, 1, 12, 6, 13, 3, 14, 7, 15 )
        var currentIdx = 13

        var player = 23 % players
        val playerScore = HashMap<Int, Int>()
        var marbleCount = 1
        for (i in 1..itrNbr step 1) {
            var score = playerScore.get(player)
            if(score == null) {
                score = 0
            }

            var counterMarbleIdx = currentIdx - 7
            if (counterMarbleIdx < 0) {
                counterMarbleIdx += board.size //-1?
            }
            val counterScore = board.removeAt(counterMarbleIdx)
            val marbleScore = marbleCount * 23
            score += marbleScore + counterScore
            //println("[$player] $board")
            playerScore.put(player, score)

            player = (player+23) % players
            currentIdx = counterMarbleIdx
            //println("currentIdx $currentIdx")

            val indexStep = 2
            var addIndex = currentIdx
            for(m in 0..21 step 1) {
                addIndex += indexStep
                if (addIndex > board.size) {
                    addIndex = (addIndex % (board.size+1))+1
                }
                val addValue = marbleCount*23+m+1
                //println("m: $m, addIndex: $addIndex, addValue: $addValue")
                board.add(addIndex, addValue)
                //println("$board")
            }
            currentIdx = addIndex
            marbleCount++
            //println("currentIdx: $currentIdx")
            //println(board)
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

    class CircleDeque<T> : ArrayDeque<T>() {
        fun rotate(num: Int) {
            if (num == 0) return
            if (num > 0) {
                for (i in 0 until num) {
                    val t = this.removeLast()
                    this.addFirst(t)
                }
            } else {
                for (i in 0 until Math.abs(num) - 1) {
                    val t = this.remove()
                    this.addLast(t)
                }
            }

        }
    }

    private fun star2b(input : Array<Int>): Long? {
        val players = input[0]
        val maxMarble = input[1]

        val playerScore = LongArray(players)
        val circle = CircleDeque<Int>()
        circle.addFirst(0)

        for(i in 1..maxMarble step 1) {
            if(i%23 == 0) {
                circle.rotate(-7)
                playerScore[i%players] = playerScore[i%players] + i + circle.pop()
            } else {
                circle.rotate(2)
                circle.addLast(i)
            }
        }

        return playerScore.max()
    }
}