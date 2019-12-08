package se.johannalynn.adventofcode.y2019

import java.util.*

object Day6 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Day.start(6, true)

        star1(scanner)
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

    class SpaceObject {
        var parent: SpaceObject?
        val name: String

        constructor(name: String, parent: SpaceObject?) {
            this.name = name
            this.parent = parent
        }

        fun countDistanceToCOM(): Int {
            if (this.parent == null) {
                return 0
            }
            return this.parent!!.countDistanceToCOM() + 1
        }
    }
}