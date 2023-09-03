package stroke

import java.awt.Color
import java.awt.Image

interface Stroke {
    fun addVertex(newVertex: Vertex, avgColor: Color)
    fun getParams(): StrokeParameters
    fun toPicture(): Image?
}