package strokes

import java.awt.Image

interface Stroke {
    fun addPoint(vertex: Vertex)
    fun getParams(): StrokeParameters
    fun toPicture(): Image
}