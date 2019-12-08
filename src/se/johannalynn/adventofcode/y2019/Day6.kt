package se.johannalynn.adventofcode.y2019

import java.util.*

object Day6 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(6, false)

        star2(scanner)
    }

    fun star1(scanner: Scanner) {
        val spaceObjects = HashMap<String, SpaceObject>()
        while(scanner.hasNextLine()) {
            val orbit = scanner.nextLine().split(")")
            var parent = spaceObjects.get(orbit[0])
            if (parent == null) {
                parent = SpaceObject(orbit[0], null)
            }
            var child = spaceObjects.get(orbit[1])
            if (child == null) {
                child = SpaceObject(orbit[1], parent)
            } else {
                child.parent = parent
            }
            spaceObjects.put(orbit[0], parent)
            spaceObjects.put(orbit[1], child)
        }
        var total = 0
        spaceObjects.values.forEach {
            //println("${it.name} - ${it.countDistanceToCOM()}")
            total += it.countDistanceToCOM()
        }
        println(total)
    }

    fun star2(scanner: Scanner) {
        val spaceObjects = HashMap<String, SpaceObject>()
        while(scanner.hasNextLine()) {
            val orbit = scanner.nextLine().split(")")
            var parent = spaceObjects.get(orbit[0])
            if (parent == null) {
                parent = SpaceObject(orbit[0], null)
            }
            var child = spaceObjects.get(orbit[1])
            if (child == null) {
                child = SpaceObject(orbit[1], parent)
            } else {
                child.parent = parent
            }
            spaceObjects.put(orbit[0], parent)
            spaceObjects.put(orbit[1], child)
        }

        // my and santas inheritance
        val me = spaceObjects.get("YOU")
        me!!.recursiveDistanceToMe(-1)
        val santa = spaceObjects.get("SAN")
        santa!!.recursiveDistanceToSanta(-1)

        var minDistance = Int.MAX_VALUE
        spaceObjects.values.forEach {
            // println("${it.name} ${it.distanceToMe} ${it.distanceToSanta}")
            val distance = it.distanceToMe + it.distanceToSanta
            if (distance < minDistance) {
                minDistance = distance
            }
        }

        println(minDistance)
    }

    class SpaceObject {
        var parent: SpaceObject?
        val name: String
        var distanceToMe: Int
        var distanceToSanta: Int

        constructor(name: String, parent: SpaceObject?) {
            this.name = name
            this.parent = parent
            this.distanceToMe = 100000
            this.distanceToSanta = 100000
        }

        fun countDistanceToCOM(): Int {
            if (this.parent == null) {
                return 0
            }
            return this.parent!!.countDistanceToCOM() + 1
        }

        fun recursiveDistanceToMe(distance: Int) {
            this.distanceToMe = distance
            if (this.parent != null) {
                this.parent!!.recursiveDistanceToMe(distance + 1)
            }
        }

        fun recursiveDistanceToSanta(distance: Int) {
            this.distanceToSanta = distance
            if (this.parent != null) {
                this.parent!!.recursiveDistanceToSanta(distance + 1)
            }
        }
    }
}