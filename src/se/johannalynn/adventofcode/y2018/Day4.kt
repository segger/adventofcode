package se.johannalynn.adventofcode.y2018

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

object Day4 {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Day4")

        val inFileName = "2018/input/day4.txt"
        val scanner = Scanner(File(inFileName))

        //star1(scanner)
        star2(scanner)
    }

    data class Record(val dateTime: LocalDateTime, val notes: String)

    private fun getSchedule(scanner: Scanner) : HashMap<Int, IntArray> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        val records = ArrayList<Record>()
        while (scanner.hasNextLine()) {
            val event = scanner.nextLine().split("]")
            val dateTime = event[0].substring(1)
            val notes = event[1].substring(1)

            records.add(Record(LocalDateTime.parse(dateTime, formatter), notes))
        }
        records.sortWith(compareBy { it.dateTime })

        val schedule = HashMap<Int, IntArray>()
        var guardId = -1
        var startMinute = -1
        for (record in records) {
            if (record.notes.contains("#")) {
                guardId = record.notes.split("#")[1].split(" ")[0].toInt()
                var guard = schedule.get(guardId)
                if (guard == null) {
                    guard = IntArray(60)
                }
                schedule.put(guardId, guard)
            }
            if (record.notes.equals("falls asleep")) {
                startMinute = record.dateTime.minute
            }
            if (record.notes.equals("wakes up")) {
                var guard = schedule.get(guardId)
                for (i in startMinute until record.dateTime.minute) {
                    guard!![i] += 1
                }
            }
        }
        return schedule
    }

    private fun star1(scanner: Scanner) {
        val schedule = getSchedule(scanner)

        var maxSleep = -1
        var maxGuard = -1
        val itr = schedule.keys.iterator()
        while(itr.hasNext()) {
            val id = itr.next()
            val slept = totalSlept(id, schedule)
            if (slept > maxSleep) {
                maxSleep = slept
                maxGuard = id
            }
        }

        val minute = mostSleepyMinute(maxGuard, schedule)
        println(maxGuard * minute[0])
    }

    private fun totalSlept(id: Int, schedule: HashMap<Int, IntArray>): Int {
        var totalSleep = 0
        val minutes = schedule.get(id)
        minutes!!.forEach { totalSleep += it }
        return totalSleep
    }

    private fun mostSleepyMinute(id: Int, schedule: HashMap<Int, IntArray>): IntArray {
        var maxSleep = -1
        var maxMinute = -1
        val minutes = schedule.get(id)
        for((index, sleep) in minutes!!.withIndex()) {
            if (sleep > maxSleep) {
                maxSleep = sleep
                maxMinute = index
            }
        }
        return intArrayOf(maxMinute, maxSleep)
    }

    private fun star2(scanner: Scanner) {
        var schedule = getSchedule(scanner)

        var maxSleep = -1
        var maxSleepMinute = -1
        var maxGuard = -1
        val itr = schedule.keys.iterator()
        while(itr.hasNext()) {
            val id = itr.next()
            var sleepy = mostSleepyMinute(id, schedule)
            if (sleepy[1] > maxSleep) {
                maxSleep = sleepy[1]
                maxSleepMinute = sleepy[0]
                maxGuard = id
            }
        }

        println(maxGuard * maxSleepMinute)
    }
}