package stroke

import java.awt.Color
import java.awt.Image

interface Stroke {
    fun addPoint(vertex: Vertex, avgColor: Color)
    fun getParams(): StrokeParameters
    fun toPicture(): Image
}