package se.johannalynn.adventofcode.y2018

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

object Day7 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(7, false)

        //star1(scanner)
        star2(60, 5, scanner)
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

    internal class Worker(val timeadd: Int) {
        var step: String = ""
        private var stepTime: Int = 0
        var available: Boolean = true

        fun startWork(step: String) {
            this.step = step
            stepTime = step.toCharArray()[0] - 'A' + 1 + timeadd
            available = false
        }

        fun work(): Boolean {
            stepTime--

            if (stepTime == 0) {
                available = true
            }

            return available
        }
    }

    private fun star2(timeadd: Int, nbrOfWorkers: Int, scanner: Scanner) {

        val prereqs = HashMap<String, ArrayList<String>>()
        val steps = HashSet<String>()
        val workingSteps = HashSet<String>()
        val done = HashMap<String, Boolean>()
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
            workingSteps.add(stepBefore)
            workingSteps.add(stepBegin)

            done.put(stepBefore, false)
            done.put(stepBegin, false)
        }

        var second = 0
        val workers = ArrayList<Worker>(nbrOfWorkers)
        for(i in 1..nbrOfWorkers) {
            workers.add(Worker(timeadd))
        }

        while(!steps.isEmpty()) {
            val availableSteps = availableSteps(workingSteps, prereqs, done)
            //print("" + second + ": ")
            for(worker in workers) {
                if(worker.available) {
                    if (availableSteps.size > 0) {
                        val step = availableSteps.removeAt(0)
                        workingSteps.remove(step)
                        //print("(" + step + ") ")
                        worker.startWork(step)
                    }
                }
            }
            for(worker in workers) {
                if (worker.work()) {
                    steps.remove(worker.step)
                    done.set(worker.step, true)
                }
            }

            //println()
            second++
        }

        //println("ANSWER: ")
        println(second)
    }

    private fun availableSteps(steps: HashSet<String>, prereqs: HashMap<String, ArrayList<String>>, done: HashMap<String, Boolean>): ArrayList<String> {
        val availableSteps = ArrayList<String>()
        for(step in steps.sorted()) {
            val pre = prereqs.get(step)
            if (pre == null) {
                availableSteps.add(step)
            } else {
                var allDone = true
                for(req in pre) {
                    if(!done.get(req)!!) {
                        allDone = false
                    }
                }
                if(allDone) {
                    availableSteps.add(step)
                }
            }
        }
        return availableSteps
    }
}