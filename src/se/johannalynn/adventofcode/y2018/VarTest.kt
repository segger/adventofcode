package se.johannalynn.adventofcode.y2018

class VarTest(private var x: Int, private var y: Int, private val v_x: Int, private val v_y: Int) {

    fun atSecond(sec: Int) {
        this.x += sec * v_x
        this.y += sec * v_y
    }
}
