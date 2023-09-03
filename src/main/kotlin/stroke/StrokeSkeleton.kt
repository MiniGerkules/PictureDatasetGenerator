package stroke

import java.awt.Color

interface StrokeSkeleton {
    fun addVertex(newVertex: Vertex, avgColor: Color)
}