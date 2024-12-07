package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*

object Day6 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(6, false)

        // star1(scanner)
        star2(scanner)
        // star2_copy2(scanner)
    }

    private fun star2(scanner: Scanner) {
        val lab = mutableListOf<Pair<Int, Int>>()
        var row = 0
        var position = Pair(-1, -1)
        var start = Pair(-1, -1)
        var maxX = 0
        while (scanner.hasNextLine()) {
            scanner.nextLine().split("").forEachIndexed { index, s ->
                if (s == "#") {
                    lab.add(Pair(index - 1, row))
                }
                if (s == "^") {
                    position = Pair(index - 1, row)
                    start = Pair(index - 1, row)
                }
                if (index > maxX) {
                    maxX = index
                }
            }
            row++
        }

        var guardPos = position
        val visited = mutableSetOf<Pair<Int, Int>>()
        var direction = Pair(0, -1)
        while (guardPos.first in 0..(maxX - 1) && guardPos.second in 0..(row - 1)) {
            visited.add(guardPos)

            val step = Pair(guardPos.first + direction.first, guardPos.second + direction.second)
            if (lab.contains(step)) {
                direction = turnRight(direction)
            } else {
                guardPos = step
            }
        }
        println("visited size: ${visited.size}")

        val startPosition = position
        val obstacles = mutableListOf<Pair<Int, Int>>()

        for ((x, y) in visited) {
            if (startPosition != Pair(x, y) && !lab.contains(Pair(x, y))) {
                val bruteForceLab = lab.toMutableList()
                bruteForceLab.add(Pair(x, y))

                var pos = startPosition
                val visited = mutableSetOf<Visited>()
                var direction = Pair(0, -1)
                var stuck = false

                while (!stuck && pos.first in 0..(maxX - 1) && pos.second in 0..(row - 1)) {
                    if (visited.contains(Visited(pos, direction))) {
                        stuck = true
                    }

                    visited.add(Visited(pos, direction))

                    val step = Pair(pos.first + direction.first, pos.second + direction.second)
                    if (bruteForceLab.contains(step)) {
                        direction = turnRight(direction)
                        // pos = Pair(pos.first + direction.first, pos.second + direction.second)
                    } else {
                        pos = step
                    }
                }

                if (stuck) {
                    print(".")
                    obstacles.add(Pair(x, y))
                }

            }
        }

        println("${obstacles.size}")
    }

    data class Visited(val position: Pair<Int, Int>, val direction: Pair<Int, Int>)

    private fun star1(scanner: Scanner) {
        val lab = mutableListOf<Pair<Int, Int>>()
        var row = 0
        var position = Pair(-1, -1)
        var maxX = 0
        while (scanner.hasNextLine()) {
            scanner.nextLine().split("").forEachIndexed { index, s ->
                if (s == "#") {
                    lab.add(Pair(index - 1, row))
                }
                if (s == "^") {
                    position = Pair(index - 1, row)
                }
                if (index > maxX) {
                    maxX = index
                }
            }
            row++
        }

        val visited = mutableSetOf<Pair<Int, Int>>()
        var direction = Pair(0, -1)
        var step = Pair(position.first + direction.first, position.second + direction.second)
        while (position.first in 0..(maxX - 1) && position.second in 0..(row - 1)) {
            visited.add(position)

            if (lab.contains(step)) {
                direction = turnRight(direction)
            }
            position = Pair(position.first + direction.first, position.second + direction.second)
            step = Pair(position.first + direction.first, position.second + direction.second)
        }
        println(visited.size)
    }

    private fun turnRight(direction: Pair<Int, Int>): Pair<Int, Int> {
        if (direction.first == 0 && direction.second == -1) {
            return Pair(1, 0)
        } else if (direction.first == 1 && direction.second == 0) {
            return Pair(0, 1)
        } else if (direction.first == 0 && direction.second == 1) {
            return Pair(-1, 0)
        } else if (direction.first == -1 && direction.second == 0) {
            return Pair(0, -1)
        }
        return Pair(0, 0)
    }

    private fun star2_bruteforce(scanner: Scanner) {
        val lab = mutableListOf<Pair<Int, Int>>()
        var row = 0
        var position = Pair(-1, -1)
        var start = Pair(-1, -1)
        var maxX = 0
        while (scanner.hasNextLine()) {
            scanner.nextLine().split("").forEachIndexed { index, s ->
                if (s == "#") {
                    lab.add(Pair(index - 1, row))
                }
                if (s == "^") {
                    position = Pair(index - 1, row)
                    start = Pair(index - 1, row)
                }
                if (index > maxX) {
                    maxX = index
                }
            }
            row++
        }

        val startPosition = position
        val obstacles = mutableListOf<Pair<Int, Int>>()

        for (x in 0..(maxX - 1)) {
            for (y in 0..(row - 1)) {
                if (!lab.contains(Pair(x, y))) {
                    val bruteForceLab = lab.toMutableList()
                    bruteForceLab.add(Pair(x, y))

                    var pos = startPosition
                    val visited = mutableSetOf<Visited>()
                    var direction = Pair(0, -1)
                    var step = Pair(pos.first + direction.first, pos.second + direction.second)
                    var stuck = false
                    var invalid = false
                    while (!stuck && !invalid && pos.first in 0..(maxX - 1) && pos.second in 0..(row - 1)) {
                        if (bruteForceLab.contains(pos) || bruteForceLab.contains(start)) {
                            invalid = true
                        }
                        visited.add(Visited(pos, direction))

                        if (visited.contains(Visited(step, direction))) {
                            stuck = true
                        }

                        if (bruteForceLab.contains(step)) {
                            direction = turnRight(direction)
                        }
                        pos = Pair(pos.first + direction.first, pos.second + direction.second)
                        step = Pair(pos.first + direction.first, pos.second + direction.second)
                    }

                    if (!invalid && stuck) {
                        print(".")
                        obstacles.add(Pair(x, y))
                    }
                }
            }
        }

        println("${obstacles.size}")
    }

    val lab = mutableListOf<Pair<Int, Int>>()
    var maxX = 0
    var row = 0
    private fun star2_recursive(scanner: Scanner) {
        var position = Pair(-1, -1)
        while (scanner.hasNextLine()) {
            scanner.nextLine().split("").forEachIndexed { index, s ->
                if (s == "#") {
                    lab.add(Pair(index - 1, row))
                }
                if (s == "^") {
                    position = Pair(index - 1, row)
                }
                if (index > maxX) {
                    maxX = index
                }
            }
            row++
        }

        val visited = mutableSetOf<Guard>()
        val direction = Pair(0, -1)

        val obstaclePositions = patroll(visited, direction, position, null)
        println(obstaclePositions)
    }

    data class Guard(val position: Pair<Int, Int>, val direction: Pair<Int, Int>)

    private fun patroll(
        currentlyVisited: MutableSet<Guard>,
        currentDirection: Pair<Int, Int>,
        currentPosition: Pair<Int, Int>,
        obstaclePosition: Pair<Int, Int>?
    ): List<Pair<Int, Int>> {
        val visited = mutableSetOf<Guard>()
        visited.addAll(currentlyVisited)
        var direction = currentDirection
        var position = currentPosition

        val obstaclePositions = mutableListOf<Pair<Int, Int>>()

        var step = Pair(position.first + direction.first, position.second + direction.second)
        while (position.first in 0..(maxX - 1) && position.second in 0..(row - 1)) {
            val guard = Guard(position, direction)
            // println("guard $guard")
            if (obstaclePosition != null && !visited.add(guard)) {
                println("visited contains ${Guard(step, direction)}: $obstaclePosition")
                return listOf(obstaclePosition)
            }
            // if lab contains step, turn right and add the position (and both directions?)
            if (lab.contains(step)) {
                direction = turnRight(direction)
                visited.add(Guard(position, direction))
            } else if (obstaclePosition == null) {
                // try put an obstacle on next step
                val obstaclePosition = Pair(position.first + direction.first, position.second + direction.second)
                // turn right
                val obstacleDirection = turnRight(direction)
                val obstacleGuard = Guard(position, obstacleDirection)
                visited.add(obstacleGuard)
                val obstacleGuardPosition =
                    Pair(position.first + obstacleDirection.first, position.second + obstacleDirection.second)
                obstaclePositions.addAll(patroll(visited, obstacleDirection, obstacleGuardPosition, obstaclePosition))
            }
            position = Pair(position.first + direction.first, position.second + direction.second)
            step = Pair(position.first + direction.first, position.second + direction.second)

        }
        println("while done $obstaclePositions")
        return obstaclePositions
    }
}
