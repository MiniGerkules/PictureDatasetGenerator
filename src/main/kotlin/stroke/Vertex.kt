package stroke

import java.awt.Color

import kotlin.math.pow
import kotlin.math.sqrt

data class Vertex(val position: Position,
                  val radius: Double,
                  val color: Color)

fun getLength(a: Vertex, b: Vertex): Double {
    return sqrt(
        (a.position.x - b.position.x).pow(2.0) +
                (a.position.y - b.position.y).pow(2.0)
    )
}
