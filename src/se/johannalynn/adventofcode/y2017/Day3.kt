package se.johannalynn.adventofcode.y2017

/**
 * 347991 -> 480
 * 347991 -> 349975
 */
object Day3 {

    @JvmStatic
    fun main(args: Array<String>) {
        // star1(347991)
        star2(347991)
    }

    private fun check(currPos: Pair<Int, Int>, direction: Pair<Int, Int>, map: Map<Pair<Int, Int>, Long>): Boolean {
        return !map.contains(Pair(currPos.first + direction.first, currPos.second + direction.second))
    }

    private fun star1(input: Long) {
        val nbrs = mutableMapOf<Pair<Int, Int>, Long>()
        nbrs[Pair(0,0)] = 1L
        nbrs[Pair(1,0)] = 2L
        nbrs[Pair(1,1)] = 3L
        nbrs[Pair(0,1)] = 4L

        val left = Pair(-1,0)
        val down = Pair(0,-1)
        val right = Pair(1,0)
        val up = Pair(0,1)

        var currDir = left
        var currPos = Pair(0,1)
        var currNbr = 5L

        while (currNbr <= input) {
            when(currDir) {
                left -> {
                    if (check(currPos, down, nbrs)) {
                        currDir = down
                    }
                }
                down -> {
                    if (check(currPos, right, nbrs)) {
                        currDir = right
                    }
                }
                right -> {
                    if (check(currPos, up, nbrs)) {
                        currDir = up
                    }
                }
                up -> {
                    if (check(currPos, left, nbrs)) {
                        currDir = left
                    }
                }
            }
            currPos = Pair(currPos.first + currDir.first, currPos.second + currDir.second)
            nbrs[currPos] = currNbr++
        }

        val distance = Math.abs(currPos.first) + Math.abs(currPos.second)
        println(distance)
    }

    private fun nbr(map: Map<Pair<Int, Int>, Long>, currPos: Pair<Int, Int>): Long {
        val right = map.getOrDefault(Pair(currPos.first + 1, currPos.second), 0)
        val right_up = map.getOrDefault(Pair(currPos.first + 1, currPos.second + 1), 0)
        val up = map.getOrDefault(Pair(currPos.first, currPos.second + 1), 0)
        val left_up = map.getOrDefault(Pair(currPos.first - 1, currPos.second + 1), 0)
        val left = map.getOrDefault(Pair(currPos.first - 1, currPos.second), 0)
        val left_down = map.getOrDefault(Pair(currPos.first - 1, currPos.second - 1), 0)
        val down = map.getOrDefault(Pair(currPos.first, currPos.second - 1), 0)
        val right_down = map.getOrDefault(Pair(currPos.first + 1, currPos.second - 1), 0)

        // println("right $right, right_up $right_up, up $up, left_up $left_up, left $left, left_down $left_down, down $down, right_down $right_down")

        return right + right_up + up + left_up + left + left_down + down + right_down
    }

    private fun star2(input: Long) {
        val nbrs = mutableMapOf<Pair<Int, Int>, Long>()
        nbrs[Pair(0,0)] = 1L
        nbrs[Pair(1,0)] = 1L
        nbrs[Pair(1,1)] = 2L
        nbrs[Pair(0,1)] = 4L

        val left = Pair(-1,0)
        val down = Pair(0,-1)
        val right = Pair(1,0)
        val up = Pair(0,1)

        var currDir = left
        var currPos = Pair(0, 1)
        var index = 5L
        var currNbr = 4L

        while (currNbr < input) {
            when(currDir) {
                left -> {
                    if (check(currPos, down, nbrs)) {
                        currDir = down
                    }
                }
                down -> {
                    if (check(currPos, right, nbrs)) {
                        currDir = right
                    }
                }
                right -> {
                    if (check(currPos, up, nbrs)) {
                        currDir = up
                    }
                }
                up -> {
                    if (check(currPos, left, nbrs)) {
                        currDir = left
                    }
                }
            }

            currPos = Pair(currPos.first + currDir.first, currPos.second + currDir.second)
            currNbr = nbr(nbrs, currPos)
            // println("$currPos - $currNbr")
            nbrs[currPos] = currNbr

            index++
        }

        println(currNbr)
    }
}