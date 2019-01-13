package se.johannalynn.adventofcode.y2018

import java.util.*
import kotlin.collections.HashMap

object Day7 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(7, false)

        star1(scanner)
    }

    private fun star1(scanner: Scanner) {

        val prereqs = HashMap<String, ArrayList<String>>()
        val steps = HashSet<String>()
        while(scanner.hasNextLine()) {
            val step = scanner.nextLine().split(" must be finished before step ")
            val stepBefore = step[0].split(" ")[1]
            val stepBegin = step[1].split(" ")[0]

            var beforeList = prereqs.get(stepBegin)
            if(beforeList == null) {
                beforeList = ArrayList()
            }
            beforeList.add(stepBefore)
            prereqs.put(stepBegin, beforeList)

            steps.add(stepBefore)
            steps.add(stepBegin)
        }

        var order = ""
        var count = 0

        while(!steps.isEmpty()) {
            var step = steps.sorted().get(count)
            val pre = prereqs.get(step)
            if (pre == null) {
                order += step
                steps.remove(step)
                count = 0
            } else {
                val wait = pre!!.map { it -> !steps.contains(it) }.all { it }
                if (wait) {
                    order += step
                    steps.remove(step)
                    count = 0
                } else {
                    count++
                }
            }
        }

        println(order)
    }
}