package se.johannalynn.adventofcode.y2024

import se.johannalynn.adventofcode.y2024.Day.start
import java.util.*
import kotlin.collections.LinkedHashSet
import kotlin.math.abs
import kotlin.math.pow

object Day13 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(13, false)

        // star1(scanner)
        star2(scanner)
    }

    data class Button(val x: Int, val y: Int)
    data class Machine(val buttonA: Button, val buttonB: Button, val prize: Button)

    private fun star1(scanner: Scanner) {

        var buttonA = Button(-1, -1)
        var buttonB = Button(-1, -1)
        var prize = Button(-1, -1)
        val machines = mutableListOf<Machine>()
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split(":")
            if (next[0].startsWith("Button")) {
                val x = Regex("X\\+([0-9]+)").find(next[1])
                val y = Regex("Y\\+([0-9]+)").find(next[1])
                if (next[0].contains("A")) {
                    buttonA = Button(x!!.groupValues[1].toInt(), y!!.groupValues[1].toInt())
                } else {
                    buttonB = Button(x!!.groupValues[1].toInt(), y!!.groupValues[1].toInt())
                }
            } else if (next[0].startsWith("Prize")) {
                val x = Regex("X=([0-9]+)").find(next[1])!!.groupValues[1].toInt()
                val y = Regex("Y=([0-9]+)").find(next[1])!!.groupValues[1].toInt()
                prize = Button(x, y)
            } else {
                machines.add(Machine(buttonA, buttonB, prize))
            }
        }
        machines.add(Machine(buttonA, buttonB, prize))

        var tokens = 0
        machines.forEach { machine ->
            // if a and b has the same divisors = more ways of solving it?

            val (a, b) = press(machine)
            if (a != -1 && b != -1) {
                println("yey")
                tokens += a * 3 + b * 1
            } else {
                println("nah")
            }
        }
        println("tokens: $tokens")
    }

    private fun press(machine: Machine): Pair<Int, Int> {
        // A = Y - B.y * B / A.y
        // =>
        // X - B.x * B = A.x * ((Y - B.y * B) / A.y)
        // A.y * X - A.y * B.x * B = A.x * Y - A.x * B.y * B
        // A.y * X - A.x * Y = B (- A.x * B.y + A.y * B.x)

        // B = (A.y * X - A.x * Y) / (A.y * B.x - A.x * B.y)

        val div = machine.buttonA.y * machine.prize.x - machine.buttonA.x * machine.prize.y
        val div2 = machine.buttonA.y * machine.buttonB.x - machine.buttonA.x * machine.buttonB.y
        if (div % div2 == 0) {
            val b = div / div2
            val a = (machine.prize.y - machine.buttonB.y * b) / machine.buttonA.y
            return Pair(a, b)
        }
        return Pair(-1, -1)
    }

    data class LongButton(val x: Long, val y: Long)
    data class LongMachine(val buttonA: LongButton, val buttonB: LongButton, val prize: LongButton)

    private fun star2(scanner: Scanner) {

        var buttonA = LongButton(-1, -1)
        var buttonB = LongButton(-1, -1)
        var prize = LongButton(-1, -1)
        val machines = mutableListOf<LongMachine>()
        while (scanner.hasNextLine()) {
            val next = scanner.nextLine().split(":")
            if (next[0].startsWith("Button")) {
                val x = Regex("X\\+([0-9]+)").find(next[1])
                val y = Regex("Y\\+([0-9]+)").find(next[1])
                if (next[0].contains("A")) {
                    buttonA = LongButton(x!!.groupValues[1].toLong(), y!!.groupValues[1].toLong())
                } else {
                    buttonB = LongButton(x!!.groupValues[1].toLong(), y!!.groupValues[1].toLong())
                }
            } else if (next[0].startsWith("Prize")) {
                val x = Regex("X=([0-9]+)").find(next[1])!!.groupValues[1].toLong()
                val y = Regex("Y=([0-9]+)").find(next[1])!!.groupValues[1].toLong()
                prize = LongButton(x + 10000000000000L, y + 10000000000000L)
            } else {
                machines.add(LongMachine(buttonA, buttonB, prize))
            }
        }
        machines.add(LongMachine(buttonA, buttonB, prize))

        var tokens = 0L
        machines.forEach { machine ->
            // if a and b has the same divisors = more ways of solving it?

            val (a, b) = longPress(machine)
            if (a != -1L && b != -1L) {
                println("yey")
                tokens += a * 3L + b * 1L
            } else {
                println("nah")
            }
        }
        println("tokens: $tokens")
    }

    private fun longPress(machine: LongMachine): Pair<Long, Long> {
        // A = Y - B.y * B / A.y
        // =>
        // X - B.x * B = A.x * ((Y - B.y * B) / A.y)
        // A.y * X - A.y * B.x * B = A.x * Y - A.x * B.y * B
        // A.y * X - A.x * Y = B (- A.x * B.y + A.y * B.x)

        // B = (A.y * X - A.x * Y) / (A.y * B.x - A.x * B.y)

        val div = machine.buttonA.y * machine.prize.x - machine.buttonA.x * machine.prize.y
        val div2 = machine.buttonA.y * machine.buttonB.x - machine.buttonA.x * machine.buttonB.y
        if (div % div2 == 0L) {
            val b = div / div2
            val a = (machine.prize.y - machine.buttonB.y * b) / machine.buttonA.y
            return Pair(a, b)
        }
        return Pair(-1, -1)
    }
}
