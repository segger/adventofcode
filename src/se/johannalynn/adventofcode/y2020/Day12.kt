package se.johannalynn.adventofcode.y2020

import se.johannalynn.adventofcode.y2020.Day.start
import java.util.*
import kotlin.math.abs

object Day12 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(12, false)

        // star1(scanner)
        // 3703 - too high
        // 1013 - too low

        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        var heading = 0 // east
        val position = Position(0, 0)
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine()
            val action = input.substring(0, 1)
            val value = Integer.parseInt(input.substring(1))

            when (action) {
                "N" -> position.y -= value
                "S" -> position.y += value
                "E" -> position.x += value
                "W" -> position.x -= value
                "L" -> {
                    heading -= value
                    // println("$action $value -> $heading (${(heading % 360)})")
                }
                "R" -> {
                    heading += value
                    // println("$action $value -> $heading (${(heading % 360)})")
                }
                "F" -> {
                    when(heading % 360) {
                        0 -> position.x += value
                        90, -270 -> position.y += value
                        -180, 180 -> position.x -= value
                        -90, 270 -> position.y -= value
                        else -> println("Woops!")
                    }
                }
            }
        }
        // println("${position.x}, ${position.y}")
        val x = abs(position.x)
        val y = abs(position.y)
        val manhattan = x + y
        println(manhattan)
    }

    private fun star2(scanner: Scanner) {
        val position = Position(0, 0)
        var waypoint = Position(10, -1)
        while (scanner.hasNextLine()) {
            val input = scanner.nextLine()
            val action = input.substring(0, 1)
            val value = Integer.parseInt(input.substring(1))

            when (action) {
                "N" -> waypoint.y -= value
                "S" -> waypoint.y += value
                "E" -> waypoint.x += value
                "W" -> waypoint.x -= value
                "L" -> {
                    waypoint = rotateWaypoint(waypoint, -value)
                }
                "R" -> {
                   waypoint = rotateWaypoint(waypoint, value)
                }
                "F" -> {
                    position.x += value * waypoint.x
                    position.y += value * waypoint.y
                }
            }
            /*
            println("${action} ${value}")
            println("${position.x}, ${position.y}")
            println("(${waypoint.x}, ${waypoint.y})")*/
        }
        // println("${position.x}, ${position.y}")
        val x = abs(position.x)
        val y = abs(position.y)
        val manhattan = x + y
        println(manhattan)
    }

    private fun rotateWaypoint(waypoint: Position, value: Int): Position {
        val currentX = waypoint.x
        val currentY = waypoint.y
        when(value % 360) {
            0 ->  {
                return Position(currentX, currentY)
            }
            90, -270 -> {
                return Position(-currentY, currentX)
            }
            -180, 180 -> {
                return Position(-currentX, -currentY)
            }
            -90, 270 -> {
                return Position(currentY, -currentX)
            }
            else -> {
                println("Woops!")
                return Position(0,0)
            }
        }
    }

    class Position(var x: Int, var y: Int) {}
}